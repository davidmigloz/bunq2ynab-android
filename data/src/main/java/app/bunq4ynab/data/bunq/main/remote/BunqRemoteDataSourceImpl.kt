package app.bunq4ynab.data.bunq.main.remote

import app.bunq4ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq4ynab.data.bunq.main.remote.dto.*
import app.bunq4ynab.data.utils.network.makeApiCall
import app.bunq4ynab.domain.model.Result
import app.bunq4ynab.domain.model.error.DataError.RemoteError
import javax.inject.Inject

internal class BunqRemoteDataSourceImpl @Inject constructor(
    private val api: BunqApi,
) : BunqRemoteDataSource {

    override suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqTriple<BunqInstallationIdDto, BunqAuthTokenDto, BunqInstallationServerPublicKeyDto>, RemoteError> {
        return makeApiCall {
            api.createInstallation(BunqInstallationRequestDto(publicKeyPemString))
        }
    }

    override suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDeviceRegistrationDto, RemoteError> {
        return makeApiCall {
            api.registerDevice(BunqDeviceRegistrationRequestDto(secret = apiKey))
        }
    }

    override suspend fun openSession(
        apiKey: String
    ): Result<BunqTriple<BunqSessionIdDto, BunqAuthTokenDto, BunqSessionUserDto>, RemoteError> {
        return makeApiCall {
            api.openSession(BunqSessionOpeningRequestDto(secret = apiKey))
        }
    }
}
