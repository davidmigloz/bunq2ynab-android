package app.bunq2ynab.domain.repository.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.bunq.BunqDevice
import app.bunq2ynab.domain.model.bunq.BunqInstallation
import app.bunq2ynab.domain.model.error.DataError

interface BunqRepository {

    suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqInstallation, DataError>

    suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDevice, DataError>
}
