package app.bunq2ynab.data.bunq.main.remote.mapper

import javax.inject.Inject

/**
 * Facade to wrap all mappers needed by BunqRepository.
 */
internal class BunqDataMappersFacade @Inject constructor(
    val bunqInstallationMapper: BunqInstallationMapper,
    val bunqDeviceMapper: BunqDeviceMapper,
)
