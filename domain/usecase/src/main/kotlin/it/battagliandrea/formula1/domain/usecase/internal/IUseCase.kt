package it.battagliandrea.formula1.domain.usecase.internal

/**
 * Interface class representing a use case that produces a result.
 *
 * @param Params The type of input parameters for the use case.
 * @param Result The type of result produced by the use case.
 */
internal interface IUseCase<in Params, out Result> {

    /**
     * Suspended function to execute the use case with the provided parameters and return the result.
     *
     * @param params The input parameters for the use case.
     * @return The result of type [Result].
     */
    abstract suspend fun execute(params: Params): Result
}
