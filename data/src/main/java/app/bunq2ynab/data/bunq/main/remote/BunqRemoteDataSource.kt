package app.bunq2ynab.data.bunq.main.remote

import app.bunq2ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.main.remote.dto.*
import app.bunq2ynab.data.bunq.main.remote.dto.BunqAuthTokenDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqDeviceRegistrationDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqSessionIdDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqSessionUserDto
import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError.RemoteError

internal interface BunqRemoteDataSource {

    suspend fun createInstallation(
        publicKeyPemString: String
    ): Result<BunqTriple<BunqInstallationIdDto, BunqAuthTokenDto, BunqInstallationServerPublicKeyDto>, RemoteError>

    suspend fun registerDevice(
        apiKey: String
    ): Result<BunqDeviceRegistrationDto, RemoteError>

    suspend fun openSession(
        apiKey: String
    ): Result<BunqTriple<BunqSessionIdDto, BunqAuthTokenDto, BunqSessionUserDto>, RemoteError>
}
