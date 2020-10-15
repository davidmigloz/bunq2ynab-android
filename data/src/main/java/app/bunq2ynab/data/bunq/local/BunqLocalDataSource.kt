package app.bunq2ynab.data.bunq.local

internal interface BunqLocalDataSource {

    suspend fun generateOAuthState(): String

    suspend fun getOAuthState(): String
}
