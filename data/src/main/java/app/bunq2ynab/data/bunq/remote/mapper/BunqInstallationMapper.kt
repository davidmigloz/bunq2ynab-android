package app.bunq2ynab.data.bunq.remote.mapper

import app.bunq2ynab.data.bunq.remote.converter.BunqTriple
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationIdDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq2ynab.data.bunq.remote.dto.BunqInstallationTokenDto
import app.bunq2ynab.data.utils.mapper.Mapper
import app.bunq2ynab.data.utils.mapper.mappingTerminalError
import app.bunq2ynab.domain.model.bunq.BunqInstallation
import javax.inject.Inject

internal class BunqInstallationMapper @Inject constructor(
) : Mapper<BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>, BunqInstallation> {

    override suspend fun invoke(
        input: BunqTriple<BunqInstallationIdDto, BunqInstallationTokenDto, BunqInstallationServerPublicKeyDto>
    ) = BunqInstallation(
        id = input.first?.id ?: mappingTerminalError("Installation id was null")
    )
}
