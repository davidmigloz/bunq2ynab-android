package app.bunq2ynab.data.bunq.remote.mapper

import javax.inject.Inject

/**
 * Facade to wrap all mappers needed by BunqRepository.
 */
internal class BunqDataMappersFacade @Inject constructor(
    val oAuthTokenExchangeResultMapper: OAuthTokenExchangeResultMapper,
    val bunqInstallationMapper: BunqInstallationMapper
)
