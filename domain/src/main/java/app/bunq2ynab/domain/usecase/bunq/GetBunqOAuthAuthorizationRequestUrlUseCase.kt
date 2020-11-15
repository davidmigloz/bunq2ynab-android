package app.bunq2ynab.domain.usecase.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.repository.bunq.BunqOAuthRepository
import app.bunq2ynab.domain.usecase.base.UseCase
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase.UseCaseParams
import javax.inject.Inject

class GetBunqOAuthAuthorizationRequestUrlUseCase @Inject constructor(
    private val bunqOAuthRepository: BunqOAuthRepository
) : UseCase<UseCaseParams, String, Exception> {

    override suspend fun invoke(params: UseCaseParams): Result<String, Exception> {
        return Result.success(
            bunqOAuthRepository.getBunqOAuthUrl(
                clientId = bunqOAuthRepository.getBunqOAuthClientId(),
                redirectUri = params.redirectUri,
                state = bunqOAuthRepository.generateOAuthState()
            ).toString()
        )
    }

    data class UseCaseParams(
        val redirectUri: String
    )
}
