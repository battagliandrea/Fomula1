package it.battagliandrea.formula1.core.resource

/**
 * A sealed class representing the result of an operation that can be either successful or an error.
 *
 * @param T The type of data contained in the resource.
 */
sealed class Resource<T> {
    /**
     * Represents a successful operation with associated data.
     *
     * @property data The data resulting from the successful operation.
     */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Represents an error that occurred during the operation.
     *
     * @property error The type of error that occurred (e.g., network error, validation error).
     */
    data class Error<T>(val error: ErrorType) : Resource<T>()
}
