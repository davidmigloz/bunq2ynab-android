package app.bunq2ynab.data.bunq.oauth.remote

import app.bunq2ynab.data.bunq.oauth.remote.dto.BunqOAuthTokenExchangeResponseDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

internal interface BunqOAuthApi {

    // https://beta.doc.bunq.com/basics/oauth#token-exchange
    @POST("token")
    suspend fun exchangeOAuthToken(
        @Query("grant_type") grantType: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Response<BunqOAuthTokenExchangeResponseDto>
}
