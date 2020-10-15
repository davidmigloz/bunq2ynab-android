package app.bunq2ynab.data.bunq.remote

import app.bunq2ynab.data.BuildConfig
import app.bunq2ynab.data.bunq.remote.dto.BunqOAuthTokenExchangeResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

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
}