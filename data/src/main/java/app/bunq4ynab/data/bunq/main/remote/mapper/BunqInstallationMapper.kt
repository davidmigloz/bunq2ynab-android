package app.bunq4ynab.data.bunq.main.remote.mapper

import app.bunq4ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq4ynab.data.bunq.main.remote.dto.BunqAuthTokenDto
import app.bunq4ynab.data.bunq.main.remote.dto.BunqInstallationIdDto
import app.bunq4ynab.data.bunq.main.remote.dto.BunqInstallationServerPublicKeyDto
import app.bunq4ynab.data.utils.mapper.Mapper
import app.bunq4ynab.data.utils.mapper.mappingErrorReturnDefault
import app.bunq4ynab.data.utils.mapper.mappingTerminalError
import app.bunq4ynab.domain.model.bunq.BunqAuthToken
import app.bunq4ynab.domain.model.bunq.BunqInstallation
import javax.inject.Inject

internal class BunqInstallationMapper @Inject constructor(
) : Mapper<BunqTriple<BunqInstallationIdDto, BunqAuthTokenDto, BunqInstallationServerPublicKeyDto>, BunqInstallation> {

    override suspend fun invoke(
        input: BunqTriple<BunqInstallationIdDto, BunqAuthTokenDto, BunqInstallationServerPublicKeyDto>
    ) = BunqInstallation(
        id = input.first?.id ?: mappingTerminalError("Installation id was null"),
        token = BunqAuthToken(
            id = input.second?.id ?: mappingTerminalError("Installation token id was null"),
            value = input.second.token ?: mappingTerminalError("Installation token value was null"),
            created = input.second.created ?: mappingErrorReturnDefault("Installation token created data was null") { "" },
            updated = input.second.updated ?: mappingErrorReturnDefault("Installation token updated date was null") { "" }
        ),
        serverPublicKey = input.third?.server_public_key ?: mappingTerminalError("Installation server public key was null"),
    )
}
