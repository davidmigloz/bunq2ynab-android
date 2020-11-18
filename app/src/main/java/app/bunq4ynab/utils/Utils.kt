package app.bunq4ynab.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun openUrlInCustomTab(context: Context, url: String) {
    openUrlInCustomTab(context, url.toUri())
}

fun openUrlInCustomTab(context: Context, uri: Uri) {
    try {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    } catch (e: Exception) {
        openUrlInBrowser(context, uri)
    }
}

fun openUrlInBrowser(context: Context, uri: Uri) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, uri).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    } catch (e: Exception) {
    }
}
