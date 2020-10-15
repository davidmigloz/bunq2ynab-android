package app.bunq2ynab.data.utils.mapper

import android.util.Log
import app.bunq2ynab.domain.model.error.DataError.MappingDtoError

internal inline fun <T> mappingErrorReturnDefault(msg: String, default: () -> T): T {
    val defaultValue = default()
    Log.e("mapping", "mappingErrorReturnDefault(): $msg | Default: $defaultValue")
    return defaultValue
}

internal fun mappingTerminalError(msg: String): Nothing {
    Log.e("mapping", "mappingTerminalError(): $msg")
    throw MappingDtoError(msg)
}
