package app.bunq2ynab.data.bunq

import android.net.Uri
import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSource
import app.bunq2ynab.data.bunq.remote.mapper.BunqDataMappersFacade
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.bunq.BunqInstallation
import app.bunq2ynab.domain.model.error.DataError
import app.bunq2ynab.domain.model.map
import app.bunq2ynab.domain.model.oauth.OAuthTokenExchangeResult
import app.bunq2ynab.domain.repository.BunqRepository
import javax.inject.Inject

internal class BunqRepositoryImpl @Inject constructor(
    private val remoteDataSource: BunqRemoteDataSource,
    private val localDataSource: BunqLocalDataSource,
    private val bunqDataMappersFacade: BunqDataMappersFacade
) : BunqRepository {

    private var accessToken: String = ""

    // ---------------------------------------------------------------------------------------------
    // OAuth
    // ---------------------------------------------------------------------------------------------

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
        bunqDataMappersFacade.oAuthTokenExchangeResultMapper(bunqOAuthTokenExchangeResponseDto)
    }

    override suspend fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    override suspend fun getAccessToken(): String = accessToken

    // ---------------------------------------------------------------------------------------------
    // API
    // ---------------------------------------------------------------------------------------------

    override suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqInstallation, DataError> = remoteDataSource.createInstallation(publicKeyPemString).map {
        bunqDataMappersFacade.bunqInstallationMapper(it)
    }
}
