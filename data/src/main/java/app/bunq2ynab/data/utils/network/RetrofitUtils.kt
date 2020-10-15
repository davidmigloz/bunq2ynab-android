package app.bunq2ynab.data.utils.network

import app.bunq2ynab.domain.model.Result
import app.bunq2ynab.domain.model.error.DataError.RemoteError
import app.bunq2ynab.domain.model.error.DataError.RemoteError.*
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal suspend fun <V : Any> makeApiCall(
    apiCall: suspend () -> Response<V>
): Result<V, RemoteError> = try {
    val response = apiCall.invoke()
    if (response.isSuccessful) {
        handleSuccess(response)
    } else {
        handleError(response)
    }
} catch (throwable: Throwable) {
    handleException(throwable)
}

private fun <V : Any> handleSuccess(response: Response<V>): Result<V, RemoteError> {
    return response.body()?.let { Result.success(it) }
        ?: Result.error(GenericRemoteError("Null body"))
}

private fun <V : Any> handleError(response: Response<V>): Result<V, RemoteError> {
    return Result.error(
        HttpError(
            httpStatusCode = response.code(),
            httpMessage = response.message() ?: "",
            body = response.errorBody()?.string()
        )
    )
}

private fun <V : Any> handleException(throwable: Throwable): Result<V, RemoteError> {
    return Result.error(
        when (throwable) {
            is SocketTimeoutException,
            is SocketException,
            is UnknownHostException -> NetworkError
            is JsonEncodingException,
            is JsonDataException -> ParsingResponseError(throwable)
            else -> GenericRemoteError(throwable)
        }
    )
}
