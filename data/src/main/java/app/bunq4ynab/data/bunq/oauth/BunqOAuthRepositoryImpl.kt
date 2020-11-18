package app.bunq4ynab.data.bunq.oauth

import android.net.Uri
import app.bunq4ynab.data.BuildConfig
import app.bunq4ynab.data.bunq.oauth.local.BunqOAuthLocalDataSource
import app.bunq4ynab.data.bunq.oauth.remote.BunqOAuthRemoteDataSource
import app.bunq4ynab.data.bunq.oauth.remote.mapper.OAuthTokenExchangeResultMapper
import app.bunq4ynab.domain.model.Result
import app.bunq4ynab.domain.model.map
import app.bunq4ynab.domain.model.oauth.OAuthTokenExchangeResult
import app.bunq4ynab.domain.repository.bunq.BunqOAuthRepository
import javax.inject.Inject

internal class BunqOAuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: BunqOAuthRemoteDataSource,
    private val localDataSource: BunqOAuthLocalDataSource,
    private val oAuthTokenExchangeResultMapper: OAuthTokenExchangeResultMapper
) : BunqOAuthRepository {

    private var accessToken: String = ""

    override suspend fun getBunqOAuthUrl(
        clientId: String,
        redirectUri: String,
        state: String
    ): Uri = Uri.parse(BuildConfig.BUNQ_OAUTH_AUTH_URL).buildUpon()
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("client_id", clientId)
        .appendQueryParameter("redirect_uri", redirectUri)
        .appendQueryParameter("state", state)
        .build()

    override suspend fun getBunqOAuthClientId(): String = BuildConfig.BUNQ_OAUTH_CLIENT_ID

    override suspend fun getBunqOAuthClientSecret(): String = BuildConfig.BUNQ_OAUTH_CLIENT_SECRET

    override suspend fun generateOAuthState(): String = localDataSource.generateOAuthState()

    override suspend fun getOAuthState(): String = localDataSource.getOAuthState()

    override suspend fun exchangeOAuthToken(
        authorizationCode: String,
        redirectUri: String,
        clientId: String,
        clientSecret: String
    ): Result<OAuthTokenExchangeResult, Exception> = remoteDataSource.exchangeOAuthToken(
        grantType = "authorization_code",
        authorizationCode, redirectUri, clientId, clientSecret
    ).map { bunqOAuthTokenExchangeResponseDto ->
        oAuthTokenExchangeResultMapper(bunqOAuthTokenExchangeResponseDto)
    }

    override suspend fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    override suspend fun getAccessToken(): String = accessToken
}
