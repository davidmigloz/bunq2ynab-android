package app.bunq4ynab.domain.repository

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate

interface KeyStoreRepository {

    suspend fun hasRsaKeyPair(keyAlias: String): Boolean

    suspend fun generateRsaKeyPair(keyAlias: String): KeyPair

    suspend fun getRsaPrivateKey(keyAlias: String): PrivateKey

    suspend fun getRsaPublicKey(keyAlias: String): PublicKey

    suspend fun getRsaCertificate(keyAlias: String): Certificate
}