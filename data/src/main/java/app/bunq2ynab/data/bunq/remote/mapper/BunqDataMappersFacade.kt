package app.bunq2ynab.data.bunq.remote.mapper

/**
 * Facade to wrap all mappers needed by BunqRepository.
 */
internal class BunqDataMappersFacade(
    val oAuthTokenExchangeResultMapper: OAuthTokenExchangeResultMapper
)
