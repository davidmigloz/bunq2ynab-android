package app.bunq2ynab.data.bunq.main.remote

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.oauth.remote.dto.BunqOAuthTokenExchangeResponseDto
import app.bunq2ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.main.remote.dto.*
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.main.remote.dto.BunqInstallationTokenDto
import retrofit2.Response
import retrofit2.http.*

internal interface BunqApi {

    @POST("/v1/installation")
    suspend fun createInstallation(
        @Body bunqInstallationRequest: BunqInstallationRequestDto
    ) : Response<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>>

    @POST("/v1/device-server")
    suspend fun registerDevice(
        @Body bunqDeviceRegistrationRequestDto: BunqDeviceRegistrationRequestDto
    ) : Response<BunqDeviceRegistrationDto>
}
