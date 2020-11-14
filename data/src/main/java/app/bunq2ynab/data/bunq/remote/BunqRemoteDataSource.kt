package app.bunq2ynab.data.bunq.remote

import app.bunq2ynab.data.bunq.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationTokenDto
import app.bunq2ynab.data.bunq.remote.dto.BunqOAuthTokenExchangeResponseDto
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError.RemoteError

internal interface BunqRemoteDataSource {

    suspend fun exchangeOAuthToken(
        grantType : String,
        authorizationCode: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): Result<BunqOAuthTokenExchangeResponseDto, RemoteError>

    suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>, RemoteError>
}
