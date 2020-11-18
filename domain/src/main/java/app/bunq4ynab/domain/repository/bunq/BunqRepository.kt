package app.bunq4ynab.domain.repository.bunq

import app.bunq4ynab.domain.model.Result
import app.bunq4ynab.domain.model.bunq.BunqDevice
import app.bunq4ynab.domain.model.bunq.BunqInstallation
import app.bunq4ynab.domain.model.bunq.BunqSession
import app.bunq4ynab.domain.model.error.DataError

interface BunqRepository {

    suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqInstallation, DataError>

    suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDevice, DataError>

    suspend fun openSession(
        apiKey: String
    ): Result<BunqSession, DataError>
}
