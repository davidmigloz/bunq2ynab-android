package app.bunq4ynab.data.bunq.oauth.remote

import app.bunq4ynab.data.bunq.oauth.remote.dto.BunqOAuthTokenExchangeResponseDto
import app.bunq4ynab.domain.model.Result
import app.bunq4ynab.domain.model.error.DataError.RemoteError

internal interface BunqOAuthRemoteDataSource {

    suspend fun exchangeOAuthToken(
        grantType: String,
        authorizationCode: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): Result<BunqOAuthTokenExchangeResponseDto, RemoteError>
}
