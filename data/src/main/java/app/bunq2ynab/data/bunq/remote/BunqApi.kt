package app.bunq2ynab.data.bunq.remote

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.remote.converter.BunqPair
import app.bunq2ynab.data.bunq.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.remote.dto.*
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationTokenDto
import app.bunq2ynab.domain.model.error.DataError
import retrofit2.Response
import retrofit2.http.*

internal interface BunqApi {

    // https://beta.doc.bunq.com/basics/oauth#token-exchange
    @POST(BuildConfig.BUNQ_OAUTH_TOKEN_URL)
    suspend fun exchangeOAuthToken(
        @Query("grant_type") grantType: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ) : Response<BunqOAuthTokenExchangeResponseDto>

    @POST("/v1/installation")
    suspend fun createInstallation(
        @Body bunqInstallationRequest: BunqInstallationRequestDto
    ) : Response<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>>
}
