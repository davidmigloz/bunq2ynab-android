package app.bunq2ynab.data.bunq.local

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

private const val PREF_NAME = "BUNQ_PREFERENCES"

private const val KEY_OAUTH_STATE = "KEY_OAUTH_STATE"

internal class BunqLocalDataSourceImpl @Inject constructor(
    @ApplicationContext appContext: Context
) : BunqLocalDataSource {

    private val pref by lazy { appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    override suspend fun generateOAuthState(): String {
        val newState = UUID.randomUUID().toString()
        pref.edit {
            putString(KEY_OAUTH_STATE, newState)
        }
        return newState
    }

    override suspend fun getOAuthState(): String = pref.getString(KEY_OAUTH_STATE, "")!!
}
