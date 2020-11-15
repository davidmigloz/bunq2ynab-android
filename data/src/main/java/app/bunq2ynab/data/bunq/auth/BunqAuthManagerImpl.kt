package app.bunq2ynab.data.bunq.auth

import app.bunq2ynab.domain.model.bunq.BunqAuthToken
import app.bunq2ynab.domain.repository.bunq.BunqAuthManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * X-Bunq-Client-Authentication
 *
 * The authentication token is used to authenticate the source of the API call.
 * It is required by all API calls except for POST /v1/installation.
 *
 * There are two different auth tokens:
 * - Installation Token:
 *   * Received it in the POST /installation call.
 *   * It has to be used when calling /device-server and /session-server endpoints.
 * - Session Token:
 *   * Received it in the POST /session-server call.
 *   * It has to be used for all the other calls.
 */
@Singleton
class BunqAuthManagerImpl @Inject constructor() : BunqAuthManager {

    override var authToken: BunqAuthToken? = null
}