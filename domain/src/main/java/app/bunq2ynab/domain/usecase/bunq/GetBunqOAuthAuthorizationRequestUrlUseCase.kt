package app.bunq2ynab.domain.usecase.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.repository.BunqRepository
import app.bunq2ynab.domain.usecase.base.UseCase
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase.UseCaseParams
import javax.inject.Inject

class GetBunqOAuthAuthorizationRequestUrlUseCase @Inject constructor(
    private val bunqRepository: BunqRepository
) : UseCase<UseCaseParams, String, Exception> {

    override suspend fun invoke(params: UseCaseParams): Result<String, Exception> {
        return Result.success(
            bunqRepository.getBunqOAuthUrl(
                clientId = bunqRepository.getBunqOAuthClientId(),
                redirectUri = params.redirectUri,
                state = bunqRepository.generateOAuthState()
            ).toString()
        )
    }

    data class UseCaseParams(
        val redirectUri: String
    )
}
