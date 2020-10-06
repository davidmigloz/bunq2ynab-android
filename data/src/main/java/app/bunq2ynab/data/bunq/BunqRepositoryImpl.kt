package app.bunq2ynab.data.bunq

import android.net.Uri
import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.remote.BunqRemoteDataSource
import app.bunq2ynab.domain.repository.BunqRepository
import javax.inject.Inject

internal class BunqRepositoryImpl @Inject constructor(
    private val remoteDataSource: BunqRemoteDataSource,
    private val localDataSource: BunqLocalDataSource
): BunqRepository {

    override suspend fun getBunqOAuthUrl(): Uri = Uri.parse(BuildConfig.BUNQ_OAUTH_AUTH_URL)

    override suspend fun getBunqOAuthClientId(): String = BuildConfig.BUNQ_OAUTH_CLIENT_ID
}
