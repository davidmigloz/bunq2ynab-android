@file:Suppress("unused")

package app.bunq2ynab.domain.usecase.base

import app.bunq2ynab.domain.model.Result

/**
 * Use this interface if you need to implement:
 * - A use case that returns a Result
 * - Input parameters are needed
 * Note: if no input parameters are needed use NoParamsUseCase instead.
 */
internal interface UseCase<in Params, out Type, out Error : Exception> {

    suspend operator fun invoke(params: Params): Result<Type, Error>
}
