package app.bunq4ynab.data.keystore

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import app.bunq4ynab.domain.repository.KeyStoreRepository
import app.bunq4ynab.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.*
import java.security.cert.Certificate
import java.util.*
import javax.inject.Inject
import javax.security.auth.x500.X500Principal

private const val ANDROID_KEYSTORE = "AndroidKeyStore"

// Bunq sdk
// https://github.com/bunq/sdk_java/blob/fde6590351fab1db825a1848d24a2f1516c5e576/src/main/java/com/bunq/sdk/context/ApiContext.java#L228
// https://github.com/bunq/sdk_java/blob/fde6590351fab1db825a1848d24a2f1516c5e576/src/main/java/com/bunq/sdk/security/SecurityUtils.java#L148

// https://developer.android.com/guide/topics/security/cryptography
// https://developer.android.com/training/articles/keystore

// https://medium.com/@abel.suviri.payan/create-rsa-key-on-android-for-sign-and-verify-9debbb566541
// https://www.baeldung.com/java-read-pem-file-keys
internal class KeyStoreRepositoryImpl @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : KeyStoreRepository {

    override suspend fun hasRsaKeyPair(keyAlias: String): Boolean = try {
        loadKeystore().containsAlias(keyAlias)
    } catch (e: Exception) {
        false
    }

    override suspend fun generateRsaKeyPair(keyAlias: String): KeyPair {
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyAlias, KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
        ).setKeySize(2048)
            .setCertificateSubject(X500Principal("CN=$keyAlias, O=bunq4ynab"))
            .setCertificateSerialNumber(BigInteger.TEN)
            .setCertificateNotBefore(GregorianCalendar().time)
            .setCertificateNotAfter(GregorianCalendar().apply { add(Calendar.YEAR, 100) }.time)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .setUserAuthenticationRequired(false)
            .build()

        val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, ANDROID_KEYSTORE).apply {
            initialize(parameterSpec)
        }
        return generator.generateKeyPair()
    }

    override suspend fun getRsaPrivateKey(keyAlias: String): PrivateKey = loadKeystore().getKey(keyAlias, null) as PrivateKey

    override suspend fun getRsaPublicKey(keyAlias: String): PublicKey = getRsaCertificate(keyAlias).publicKey

    override suspend fun getRsaCertificate(keyAlias: String): Certificate = loadKeystore().getCertificate(keyAlias)

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun loadKeystore() = withContext(coroutineContextProvider.io) {
        KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
    }
}
