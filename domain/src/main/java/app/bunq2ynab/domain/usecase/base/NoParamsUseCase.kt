@file:Suppress("unused")

package app.bunq2ynab.domain.usecase.base

import app.bunq2ynab.domain.model.Result

/**
 * Use this interface if you need to implement:
 * - A use case that returns a Result
 * - No input parameters are needed
 * Note: if input parameters are needed use UseCase instead.
 */
internal interface NoParamsUseCase<out Type, out Error : Exception> : UseCase<NoParams, Type, Error> {

    suspend operator fun invoke(): Result<Type, Error> = invoke(NoParams)
}

internal typealias NoParams = Unit
