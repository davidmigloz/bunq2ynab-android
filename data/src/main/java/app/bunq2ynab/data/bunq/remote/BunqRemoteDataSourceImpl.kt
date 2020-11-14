package app.bunq2ynab.data.bunq.remote

import app.bunq2ynab.data.bunq.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.remote.dto.*
import app.bunq2ynab.data.utils.network.makeApiCall
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError.RemoteError
import javax.inject.Inject

internal class BunqRemoteDataSourceImpl @Inject constructor(
    private val api: BunqApi,
) : BunqRemoteDataSource {

    override suspend fun exchangeOAuthToken(
        grantType: String,
        authorizationCode: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): Result<BunqOAuthTokenExchangeResponseDto, RemoteError> {
        return makeApiCall {
            api.exchangeOAuthToken(grantType, authorizationCode, redirectUri, clientId, clientSecret)
        }
    }

    override suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>, RemoteError> {
        return makeApiCall {
            api.createInstallation(BunqInstallationRequestDto(publicKeyPemString))
        }
    }
}
