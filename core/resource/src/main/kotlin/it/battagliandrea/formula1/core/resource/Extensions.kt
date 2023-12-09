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

/**
 * Applies the given functions [isSuccess] and [isError] to the elements of the flow and
 * returns a new flow of the results.
 *
 * This function is similar to the `fold` function on collections but operates on a flow of [Resource].
 *
 * @param isSuccess A suspend function to be applied if the resource is of type [Resource.Success].
 *                  It takes the success value and returns the result.
 * @param isError A suspend function to be applied if the resource is of type [Resource.Error].
 *                It takes the error value and returns the result.
 * @return A new flow containing the results of applying [isSuccess] to [Resource.Success] elements
 *         and [isError] to [Resource.Error] elements.
 */
inline fun <T, R> Flow<Resource<T>>.fold(
    crossinline isSuccess: suspend (value: T) -> R,
    crossinline isError: suspend (value: ErrorType) -> R,
): Flow<R> = transform { value ->
    when (value) {
        is Resource.Success -> emit(isSuccess(value.data))
        is Resource.Error -> emit(isError(value.error))
    }
}

/**
 * Transforms the success values of the given flow using the provided [transform] function.
 *
 * This function is similar to the `map` function on collections but operates on a flow of [Resource].
 * Only the success values are transformed; error values are passed through unchanged.
 *
 * @param transform A suspend function to be applied to each success value, transforming it to a new value of type [O].
 * @return A new flow containing the transformed success values while leaving error values unchanged.
 */
inline fun <I, O> Flow<Resource<I>>.mapSuccess(crossinline transform: suspend (value: I) -> O): Flow<Resource<O>> = transform { value ->
    require(value is Resource.Success)
    emit(Resource.Success(transform(value.data)))
}

/**
 * Transforms the success values of the given flow using the provided [transform] function.
 *
 * This function is an extension of Flow<Resource<I>> and applies the [transform] function to each success value,
 * producing a new flow of Resource<O>. Error values are passed through unchanged.
 *
 * @param transform A suspend function to be applied to each success value, transforming it to a new value of type [O].
 * @return A new flow containing the transformed success values while leaving error values unchanged.
 */
inline fun <I> Flow<Resource<I>>.mapError(crossinline transform: suspend (value: ErrorType) -> ErrorType): Flow<Resource<I>> = transform { value ->
    require(value is Resource.Error)
    emit(Resource.Error(transform(value.error)))
}
