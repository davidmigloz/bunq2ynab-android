package app.bunq4ynab.utils.crypt

import android.util.Base64
import java.security.PublicKey

fun PublicKey.toPemString(): String {
    val publicKeyBase64: String = Base64.encodeToString(this.encoded, Base64.NO_WRAP)
    return publicKeyBase64.chunked(64).joinToString(
        separator = "\n",
        prefix = "-----BEGIN PUBLIC KEY-----\n",
        postfix = "\n-----END PUBLIC KEY-----\n"
    )
}
