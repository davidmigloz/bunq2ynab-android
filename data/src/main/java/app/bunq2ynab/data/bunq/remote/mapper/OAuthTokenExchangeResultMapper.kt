package app.bunq2ynab.data.bunq.remote.mapper

import app.bunq2ynab.data.bunq.remote.dto.BunqOAuthTokenExchangeResponseDto
import app.bunq2ynab.data.utils.mapper.mappingErrorReturnDefault
import app.bunq2ynab.data.utils.mapper.mappingTerminalError
import app.bunq2ynab.domain.model.oauth.OAuthTokenExchangeResult
import app.bunq2ynab.domain.model.oauth.OAuthTokenExchangeResult.TokenType

internal typealias OAuthTokenExchangeResultMapper = (BunqOAuthTokenExchangeResponseDto) -> OAuthTokenExchangeResult

internal fun oAuthTokenExchangeResultMapper(
    input: BunqOAuthTokenExchangeResponseDto
) = OAuthTokenExchangeResult(
    accessToken = input.access_token ?: mappingTerminalError("invalid access token"),
    tokenType = when (val type = input.token_type) {
        "bearer" -> TokenType.Bearer
        else -> mappingErrorReturnDefault("invalid token type $type") { TokenType.Bearer }
    },
    state = input.state ?: mappingErrorReturnDefault("invalid state") { "" }
)
