package app.bunq4ynab.domain.repository.bunq

import app.bunq4ynab.domain.model.bunq.BunqAuthToken

interface BunqAuthManager {
    var authToken: BunqAuthToken?
}