package app.bunq2ynab.data.utils.repository

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Creates a flow of Result<T, DataError> that catches
 * and handle exception of the block by default (calling handleDataError()).
 */
internal fun <T> safeResultFlow(
    block: suspend FlowCollector<Result<T, DataError>>.() -> Unit
): Flow<Result<T, DataError>> = flow(block).handleDataError()

/**
 * Catches flow exceptions, wraps them in a Result object and emits it.
 * If the exception is not a DataError, it wraps the exception in GenericDataError.
 */
internal fun <T> Flow<Result<T, DataError>>.handleDataError(): Flow<Result<T, DataError>> =
    catch { e ->
        val dataError = if (e is DataError) e else DataError.GenericDataError(e)
        emit(Result.error(dataError))
    }
