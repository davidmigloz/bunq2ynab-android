package app.bunq4ynab.data.bunq.main.remote

import app.bunq4ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq4ynab.data.bunq.main.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface BunqApi {

    // https://doc.bunq.com/#/installation
    @POST("installation")
    suspend fun createInstallation(
        @Body bunqInstallationRequest: BunqInstallationRequestDto
    ) : Response<BunqTriple<BunqInstallationIdDto, BunqAuthTokenDto, BunqInstallationServerPublicKeyDto>>

    // https://doc.bunq.com/#/device-server
    @POST("device-server")
    suspend fun registerDevice(
        @Body bunqDeviceRegistrationRequestDto: BunqDeviceRegistrationRequestDto
    ) : Response<BunqDeviceRegistrationDto>

    // https://doc.bunq.com/#/session-server
    @POST("session-server")
    suspend fun openSession(
        @Body bunqSessionOpeningRequestDto: BunqSessionOpeningRequestDto
    ) : Response<BunqTriple<BunqSessionIdDto, BunqAuthTokenDto, BunqSessionUserDto>>
}
