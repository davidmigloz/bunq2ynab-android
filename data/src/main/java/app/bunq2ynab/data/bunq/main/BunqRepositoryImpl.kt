package app.bunq2ynab.data.bunq.main

import android.net.Uri
import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.main.local.BunqLocalDataSource
import app.bunq2ynab.data.bunq.main.remote.BunqRemoteDataSource
import app.bunq2ynab.data.bunq.oauth.remote.BunqOAuthRemoteDataSource
import app.bunq2ynab.data.bunq.main.remote.mapper.BunqDataMappersFacade
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.bunq.BunqDevice
import app.bunq2ynab.domain.model.bunq.BunqInstallation
import app.bunq2ynab.domain.model.error.DataError
import app.bunq2ynab.domain.model.map
import app.bunq2ynab.domain.model.oauth.OAuthTokenExchangeResult
import app.bunq2ynab.domain.repository.bunq.BunqRepository
import javax.inject.Inject

internal class BunqRepositoryImpl @Inject constructor(
    private val remoteDataSource: BunqRemoteDataSource,
    private val localDataSource: BunqLocalDataSource,
    private val mappers: BunqDataMappersFacade
) : BunqRepository {

    override suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqInstallation, DataError> = remoteDataSource.createInstallation(publicKeyPemString).map {
        mappers.bunqInstallationMapper(it)
    }

    override suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDevice, DataError> = remoteDataSource.registerDevice(apiKey).map {
        mappers.bunqDeviceMapper(it)
    }
}
