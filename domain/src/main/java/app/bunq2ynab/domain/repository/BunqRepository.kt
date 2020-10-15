package app.bunq2ynab.domain.repository

import android.net.Uri
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.oauth.OAuthTokenExchangeResult

interface BunqRepository {

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
