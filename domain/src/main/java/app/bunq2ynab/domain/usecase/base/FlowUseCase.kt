@file:Suppress("unused")

package app.bunq2ynab.domain.usecase.base

import app.bunq2ynab.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use this interface if you need to implement:
 * - A use case that returns flow
 * - Input parameters are needed
 * Note: if no input parameters are needed use NoParamsFlowUseCase instead.
 */
internal interface FlowUseCase<in Params, out Type, out Error : Exception> {

    operator fun invoke(params: Params): Flow<Result<Type, Error>>
}
