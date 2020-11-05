package app.bunq2ynab.domain.usecase.bunq

import android.util.Log
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.repository.BunqRepository
import app.bunq2ynab.domain.repository.KeyStoreRepository
import app.bunq2ynab.domain.usecase.base.FlowUseCase
import app.bunq2ynab.domain.usecase.bunq.CreateBunqApiContextUseCase.UseCaseParams
import app.bunq2ynab.utils.crypt.toPemString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val BUNQ_RSA_KEY_ALIAS = "cwByQdObv4RgFniqMW5p"

class CreateBunqApiContextUseCase @Inject constructor(
    private val bunqRepository: BunqRepository,
    private val keyStoreRepository: KeyStoreRepository
) : FlowUseCase<UseCaseParams, ApiContextState, Exception> {

    override fun invoke(params: UseCaseParams): Flow<Result<ApiContextState, Exception>> {
        return flow {
            val publicKeyPemString = generateRsaKeyPair()
            registerInstallation(publicKeyPemString)
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
     */
    private suspend fun registerInstallation(publicKeyPemString: String) {

    }

    data class UseCaseParams(val apiKey: String)
}

sealed class ApiContextState {

}