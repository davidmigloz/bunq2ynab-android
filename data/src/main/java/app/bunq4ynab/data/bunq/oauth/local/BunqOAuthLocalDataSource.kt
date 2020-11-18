package app.bunq4ynab.data.bunq.oauth.local

internal interface BunqOAuthLocalDataSource {

    suspend fun generateOAuthState(): String

    suspend fun getOAuthState(): String
}
