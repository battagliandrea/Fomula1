package it.battagliandrea.formula1.domain.usecase.internal

internal interface UseCase<in Params, out T> {
    suspend fun execute(params: Params): T
}
