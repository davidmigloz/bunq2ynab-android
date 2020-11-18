package app.bunq4ynab.data.bunq.oauth.remote.mapper

import app.bunq4ynab.data.bunq.oauth.remote.dto.BunqOAuthTokenExchangeResponseDto
import app.bunq4ynab.data.utils.mapper.Mapper
import app.bunq4ynab.data.utils.mapper.mappingErrorReturnDefault
import app.bunq4ynab.data.utils.mapper.mappingTerminalError
import app.bunq4ynab.domain.model.oauth.OAuthTokenExchangeResult
import app.bunq4ynab.domain.model.oauth.OAuthTokenExchangeResult.TokenType
import javax.inject.Inject

internal class OAuthTokenExchangeResultMapper @Inject constructor(
) : Mapper<BunqOAuthTokenExchangeResponseDto, OAuthTokenExchangeResult> {

    override suspend fun invoke(input: BunqOAuthTokenExchangeResponseDto): OAuthTokenExchangeResult =
        OAuthTokenExchangeResult(
            accessToken = input.access_token ?: mappingTerminalError("invalid access token"),
            tokenType = when (val type = input.token_type) {
                "bearer" -> TokenType.Bearer
                else -> mappingErrorReturnDefault("invalid token type $type") { TokenType.Bearer }
            },
            state = input.state ?: mappingErrorReturnDefault("invalid state") { "" }
        )
}
