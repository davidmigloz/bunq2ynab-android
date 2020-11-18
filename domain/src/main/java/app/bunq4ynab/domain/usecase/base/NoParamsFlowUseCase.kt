@file:Suppress("unused")

package app.bunq4ynab.domain.usecase.base

import app.bunq4ynab.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Use this interface if you need to implement:
 * - A use case that returns flow
 * - No input parameters are needed
 * Note: if input parameters are needed use FlowUseCase instead.
 */
internal interface NoParamsFlowUseCase<out Type, out Error : Exception> :
    FlowUseCase<NoParams, Type, Error> {

    operator fun invoke(): Flow<Result<Type, Error>> = invoke(NoParams)
}
