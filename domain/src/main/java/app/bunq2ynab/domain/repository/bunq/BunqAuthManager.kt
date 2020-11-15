package app.bunq2ynab.domain.repository.bunq

import app.bunq2ynab.domain.model.bunq.BunqAuthToken

interface BunqAuthManager {
    var authToken: BunqAuthToken?
}