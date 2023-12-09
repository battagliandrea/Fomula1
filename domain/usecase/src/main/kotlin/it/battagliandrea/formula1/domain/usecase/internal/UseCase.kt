package it.battagliandrea.formula1.domain.usecase.internal

import it.battagliandrea.formula1.core.resource.Resource
import kotlinx.coroutines.flow.Flow

internal interface UseCase<Params, T> {
    operator fun invoke(params: Params): Flow<Resource<T>>
}
