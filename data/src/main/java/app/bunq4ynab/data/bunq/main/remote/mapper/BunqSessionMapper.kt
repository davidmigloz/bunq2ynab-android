package app.bunq4ynab.data.bunq.main.remote.mapper

import app.bunq4ynab.data.bunq.main.remote.converter.BunqTriple
import app.bunq4ynab.data.bunq.main.remote.dto.BunqAuthTokenDto
import app.bunq4ynab.data.bunq.main.remote.dto.BunqSessionIdDto
import app.bunq4ynab.data.bunq.main.remote.dto.BunqSessionUserDto
import app.bunq4ynab.data.utils.mapper.Mapper
import app.bunq4ynab.data.utils.mapper.mappingErrorReturnDefault
import app.bunq4ynab.data.utils.mapper.mappingTerminalError
import app.bunq4ynab.domain.model.bunq.BunqAuthToken
import app.bunq4ynab.domain.model.bunq.BunqSession
import javax.inject.Inject

internal class BunqSessionMapper @Inject constructor(
) : Mapper<BunqTriple<BunqSessionIdDto, BunqAuthTokenDto, BunqSessionUserDto>, BunqSession> {

    override suspend fun invoke(
        input: BunqTriple<BunqSessionIdDto, BunqAuthTokenDto, BunqSessionUserDto>
    ) = BunqSession(
        id = input.first?.id ?: mappingTerminalError("Session id was null"),
        token = BunqAuthToken(
            id = input.second?.id ?: mappingTerminalError("Session token id was null"),
            value = input.second.token ?: mappingTerminalError("Session token value was null"),
            created = input.second.created ?: mappingErrorReturnDefault("Session token created data was null") { "" },
            updated = input.second.updated ?: mappingErrorReturnDefault("Session token updated date was null") { "" }
        ),
        userId = input.third?.id ?: mappingTerminalError("User id was null"),
    )
}
