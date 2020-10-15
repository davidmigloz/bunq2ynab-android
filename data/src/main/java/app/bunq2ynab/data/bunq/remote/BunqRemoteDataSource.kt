package app.bunq2ynab.data.bunq.remote

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
}
