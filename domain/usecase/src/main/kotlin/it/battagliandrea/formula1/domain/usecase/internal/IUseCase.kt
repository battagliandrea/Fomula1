package it.battagliandrea.formula1.domain.usecase.internal

import it.battagliandrea.formula1.core.resource.Resource
import kotlinx.coroutines.flow.Flow

internal interface IUseCase<in P, T> {
    operator fun invoke(params: P): Flow<Resource<T>>
}
