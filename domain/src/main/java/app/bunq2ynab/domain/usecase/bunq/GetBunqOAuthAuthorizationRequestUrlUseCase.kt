package app.bunq2ynab.domain.usecase.bunq

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.repository.BunqRepository
import app.bunq2ynab.domain.usecase.base.UseCase
import javax.inject.Inject

class GetBunqOAuthAuthorizationRequestUrlUseCase @Inject constructor(
    private val bunqRepository: BunqRepository
) : UseCase<GetBunqOAuthAuthorizationRequestUrlUseCase.Params, String, Exception> {

    override suspend fun invoke(params: Params): Result<String, Exception> {
        return Result.success(
            bunqRepository.getBunqOAuthUrl().buildUpon()
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", bunqRepository.getBunqOAuthClientId())
                .appendQueryParameter("redirect_uri", params.redirectUri)
                .appendQueryParameter("state", "myState")
                .build().toString()
        )
    }

    data class Params(
        val redirectUri: String
    )
}
