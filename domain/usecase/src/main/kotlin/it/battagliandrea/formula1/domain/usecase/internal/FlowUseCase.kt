package it.battagliandrea.formula1.domain.usecase.internal

internal interface FlowUseCase<in Params, out T> {
    fun execute(
        params: Params,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): T
}
