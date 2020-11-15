package app.bunq2ynab.domain.usecase.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.bunq.BunqDevice
import app.bunq2ynab.domain.model.bunq.BunqInstallation
import app.bunq2ynab.domain.repository.bunq.BunqRepository
import app.bunq2ynab.domain.repository.KeyStoreRepository
import app.bunq2ynab.domain.repository.bunq.BunqAuthManager
import app.bunq2ynab.domain.repository.bunq.BunqOAuthRepository
import app.bunq2ynab.domain.usecase.base.NoParams
import app.bunq2ynab.domain.usecase.base.NoParamsFlowUseCase
import app.bunq2ynab.utils.crypt.toPemString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val BUNQ_RSA_KEY_ALIAS = "cwByQdObv4RgFniqMW5p"

class CreateBunqApiContextUseCase @Inject constructor(
    private val keyStoreRepository: KeyStoreRepository,
    private val bunqRepository: BunqRepository,
    private val bunqOAuthRepository: BunqOAuthRepository,
    private val bunqAuthManager: BunqAuthManager
) : NoParamsFlowUseCase<ApiContextState, Exception> {

    override fun invoke(params: NoParams): Flow<Result<ApiContextState, Exception>> {
        return flow<Result<ApiContextState, Exception>> {
            // 1. DeviceRsaKeyPairGeneration
            emit(Result.success(ApiContextState.DeviceRsaKeyPairGeneration))
            val publicKeyPemString = generateRsaKeyPair()
            // 2. InstallationRegistration
            emit(Result.success(ApiContextState.InstallationRegistration))
            registerInstallation(publicKeyPemString)
            // 3. DeviceRegistration
            emit(Result.success(ApiContextState.DeviceRegistration))
            registerDevice()
            // 4. Open a session
            emit(Result.success(ApiContextState.SessionOpening))
        }.catch { e ->
            val exception = if(e is Exception) e else Exception(e)
            emit(Result.error(exception))
        }
    }

    /**
     * Each call to bunq needs to be signed with our own private 2048 bit RSA key.
     * This method generates the key pair and stores it in the Android Keystore.
     * @return public key in PEM string format
     */
    private suspend fun generateRsaKeyPair(): String {
        if (!keyStoreRepository.hasRsaKeyPair(BUNQ_RSA_KEY_ALIAS)) {
            keyStoreRepository.generateRsaKeyPair(BUNQ_RSA_KEY_ALIAS)
        }
        val publicKey = keyStoreRepository.getRsaPublicKey(BUNQ_RSA_KEY_ALIAS)
        return publicKey.toPemString()
    }

    /**
     * An installation is used to tell the server about the public key of your key pair.
     * The server uses this key to verify your subsequent calls.
     * This method registers the given RSA public key in bunq.
     *
     * You provide the server with the public part of the key pair that you are going to
     * use to create the value of the signature header for all future API calls.
     * The server creates an installation for you. Store the Installation Token and
     * ServerPublicKey from the response. This token is used in the "X-Bunq-Client-Authentication"
     * header for the creation of a DeviceServer and SessionServer.
     */
    private suspend fun registerInstallation(publicKeyPemString: String): BunqInstallation {
        return bunqRepository.createInstallation(publicKeyPemString).get().apply {
            bunqAuthManager.authToken = token
        }
    }

    /**
     * Further calls made to the server need to come from a registered device.
     * This step allows registering a device and the IP address(es) it uses to connect to the bunq API.
     */
    private suspend fun registerDevice(): BunqDevice {
        return bunqRepository.registerDevice(bunqOAuthRepository.getAccessToken()).get()
    }
}

sealed class ApiContextState {

    object DeviceRsaKeyPairGeneration: ApiContextState()
    object InstallationRegistration: ApiContextState()
    object DeviceRegistration: ApiContextState()
    object SessionOpening: ApiContextState()

}