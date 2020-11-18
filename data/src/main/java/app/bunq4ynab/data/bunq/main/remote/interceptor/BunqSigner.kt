package app.bunq4ynab.data.bunq.main.remote.interceptor

import app.bunq4ynab.data.utils.crypt.Sha256RsaSigner
import app.bunq4ynab.data.utils.crypt.signToBase64
import app.bunq4ynab.domain.repository.KeyStoreRepository
import app.bunq4ynab.domain.usecase.bunq.BUNQ_RSA_KEY_ALIAS
import javax.inject.Inject

/**
 * https://beta.doc.bunq.com/basics/authentication/signing
 *
 * The signatures are created using the SHA256 cryptographic hash function
 * and are included (already encoded in Base64) in the X-Bunq-Client-Signature
 * request header and the X-Bunq-Server-Signature response header.
 *
 * For signing requests, the client must use the private key corresponding to the
 * public key that was sent to the server in the installation API call.
 * That public key is what the server will use to verify the signature when it
 * receives the request. In that same call the server will respond with a server
 * side public key, which the client must use to verify the server's signatures.
 * The generated RSA key pair must have key lengths of 2048 bits and adhere to the
 * PKCS #8 standard.
 */
internal class BunqSigner @Inject constructor(
    private val keyStoreRepository: KeyStoreRepository,
    private val sha256RsaSigner: Sha256RsaSigner
) {

    suspend fun sign(body: String): String = sha256RsaSigner.signToBase64(
        message = body,
        privateKey = keyStoreRepository.getRsaPrivateKey(BUNQ_RSA_KEY_ALIAS)
    )
}