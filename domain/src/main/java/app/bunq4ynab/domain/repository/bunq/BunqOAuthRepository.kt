package app.bunq4ynab.domain.repository.bunq

import android.net.Uri
import app.bunq4ynab.domain.model.Result
import app.bunq4ynab.domain.model.oauth.OAuthTokenExchangeResult

interface BunqOAuthRepository {

    suspend fun getBunqOAuthUrl(
        clientId: String,
        redirectUri: String,
        state: String
    ): Uri

    suspend fun getBunqOAuthClientId(): String

    suspend fun getBunqOAuthClientSecret(): String

    suspend fun generateOAuthState(): String

    suspend fun getOAuthState(): String

    suspend fun exchangeOAuthToken(
        authorizationCode: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): Result<OAuthTokenExchangeResult, Exception>

    suspend fun setAccessToken(accessToken: String)

    suspend fun getAccessToken(): String
}
