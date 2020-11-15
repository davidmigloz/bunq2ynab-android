package app.bunq2ynab.domain.usecase.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.map
import app.bunq2ynab.domain.repository.bunq.BunqOAuthRepository
import app.bunq2ynab.domain.usecase.base.UseCase
import app.bunq2ynab.domain.usecase.bunq.ExchangeBunqOAuthTokenUseCase.UseCaseParams
import javax.inject.Inject

class ExchangeBunqOAuthTokenUseCase @Inject constructor(
    private val bunqOAuthRepository: BunqOAuthRepository
) : UseCase<UseCaseParams, Unit, Exception> {

    override suspend fun invoke(params: UseCaseParams): Result<Unit, Exception> {
        return Result.of {
            checkState(params.state)
            bunqOAuthRepository.exchangeOAuthToken(
                authorizationCode = params.authorizationCode,
                redirectUri = params.redirectUri,
                clientId = bunqOAuthRepository.getBunqOAuthClientId(),
                clientSecret = bunqOAuthRepository.getBunqOAuthClientSecret()
            ).map { oAuthTokenExchangeResult ->
                checkState(oAuthTokenExchangeResult.state)
                bunqOAuthRepository.setAccessToken(oAuthTokenExchangeResult.accessToken)
            }
        }
    }

    private suspend fun checkState(state: String) {
        val originalState = bunqOAuthRepository.getOAuthState()
        if (originalState != state) throw OAuthStateException("State has changed, possible CSRF attack!")
    }

    data class UseCaseParams(
        val redirectUri: String,
        val authorizationCode: String,
        val state: String
    )

    class OAuthStateException(msg: String) : Exception(msg)
}