@file:Suppress("unused")

package app.bunq2ynab.domain.model.error

/**
 * Errors of the data layer.
 */
sealed class DataError : Exception {

    constructor(cause: Throwable? = null) : super(cause)
    constructor(msg: String) : super(msg)

    /**
     * Specific errors for remote data sources.
     */
    sealed class RemoteError : DataError {

        constructor(cause: Throwable? = null) : super(cause)
        constructor(msg: String) : super(msg)

        /**
         * Http 4xx client errors and 5xx server errors.
         */
        data class HttpError(
            val httpStatusCode: Int,
            val httpMessage: String = "",
            val body: String? = null
        ) : RemoteError()

        /**
         * Error while parsing an HTTP response body.
         */
        class ParsingResponseError(cause: Throwable) : RemoteError(cause)

        /**
         * No internet error.
         */
        object NetworkError : RemoteError()

        /**
         * Any other error from the remote data source.
         */
        class GenericRemoteError : RemoteError {
            constructor(cause: Throwable? = null) : super(cause)
            constructor(msg: String) : super(msg)
        }
    }

    /**
     * Error while mapping a DTO.
     */
    class MappingDtoError(msg: String) : DataError(msg)

    /**
     * Any other error from the data layer.
     */
    class GenericDataError : DataError {
        constructor(cause: Throwable? = null) : super(cause)
        constructor(msg: String) : super(msg)
    }
}
