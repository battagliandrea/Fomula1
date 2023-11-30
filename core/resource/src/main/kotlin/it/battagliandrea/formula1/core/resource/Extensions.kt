package it.battagliandrea.formula1.core.resource

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
