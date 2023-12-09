package it.battagliandrea.formula1.core.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Extension function to convert a value of type T to a successful Resource.
 *
 * @receiver The value of type T to be wrapped in a successful Resource.
 * @return A [Resource.Success] containing the provided value.
 */
inline fun <reified T> T.toResourceSuccess(): Resource<T> = Resource.Success(this)

/**
 * Extension function to convert a value of type ErrorType to an error Resource.
 * Note: It is assumed that the value represents an error type.
 *
 * @receiver The value of type [ErrorType] to be wrapped in an error Resource.
 * @return A [Resource.Error] containing the provided value.
 */
inline fun <reified T> ErrorType.toResourceError(): Resource<T> = Resource.Error(this)

inline fun <T, R> Flow<Resource<T>>.fold(
    crossinline isSuccess: suspend (value: T) -> R,
    crossinline isError: suspend (value: ErrorType) -> R,
): Flow<R> = transform { value ->
    when (value) {
        is Resource.Success -> emit(isSuccess(value.data))
        is Resource.Error -> emit(isError(value.error))
    }
}

inline fun <I, O> Flow<Resource<I>>.mapSuccess(crossinline transform: suspend (value: I) -> O): Flow<Resource<O>> = transform { value ->
    require(value is Resource.Success)
    emit(Resource.Success(transform(value.data)))
}

inline fun <I> Flow<Resource<I>>.mapError(crossinline transform: suspend (value: ErrorType) -> ErrorType): Flow<Resource<I>> = transform { value ->
    require(value is Resource.Error)
    emit(Resource.Error(transform(value.error)))
}
