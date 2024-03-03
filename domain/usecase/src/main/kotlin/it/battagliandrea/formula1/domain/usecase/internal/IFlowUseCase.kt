package it.battagliandrea.formula1.domain.usecase.internal

import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a use case that produces a result as a [Flow].
 *
 * @param Params The type of input parameters for the use case.
 * @param Result The type of result produced by the use case.
 */
internal interface IFlowUseCase<in Params, out Result> {

    /**
     * Executes the use case with the provided parameters and returns the result as a [Flow].
     *
     * @param params The input parameters for the use case.
     * @return A [Flow] emitting the result of type [Result].
     */
    abstract fun execute(params: Params): Flow<Result>
}
