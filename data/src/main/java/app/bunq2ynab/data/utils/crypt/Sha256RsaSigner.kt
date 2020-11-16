package app.bunq2ynab.data.utils.crypt

import android.util.Base64
import app.bunq2ynab.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.withContext
import java.security.PrivateKey
import java.security.Signature
import javax.inject.Inject

private const val ALGORITHM_SHA_256_RSA = "SHA256withRSA"

internal class Sha256RsaSigner @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) {

    suspend fun sign(
        message: ByteArray,
        privateKey: PrivateKey
    ): ByteArray = withContext(coroutineContextProvider.default) {
        val signer = Signature.getInstance(ALGORITHM_SHA_256_RSA).apply {
            initSign(privateKey)
            update(message)
        }
        signer.sign()
    }
}

internal suspend fun Sha256RsaSigner.signToBase64(
    message: ByteArray,
    privateKey: PrivateKey
): String = Base64.encodeToString(sign(message, privateKey), Base64.NO_WRAP)

internal suspend fun Sha256RsaSigner.signToBase64(
    message: String,
    privateKey: PrivateKey
): String = signToBase64(message.toByteArray(), privateKey)
