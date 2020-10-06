package app.bunq2ynab.domain.repository

import android.net.Uri

interface BunqRepository {

    suspend fun getBunqOAuthUrl(): Uri

    suspend fun getBunqOAuthClientId(): String
}