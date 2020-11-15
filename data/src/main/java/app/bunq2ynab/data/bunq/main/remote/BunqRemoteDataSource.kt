package app.bunq2ynab.data.bunq.main.remote

import app.bunq2ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.main.remote.dto.BunqDeviceRegistrationDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationTokenDto
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError.RemoteError

internal interface BunqRemoteDataSource {

    suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>, RemoteError>

    suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDeviceRegistrationDto, RemoteError>
}
