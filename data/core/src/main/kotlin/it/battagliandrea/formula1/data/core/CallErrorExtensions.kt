package it.battagliandrea.formula1.data.core

import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import it.battagliandrea.formula1.domain.models.ErrorType

fun CallError.toErrorType(): ErrorType =
    when (this) {
        is HttpError -> {
            when (this.code) {
                404 -> ErrorType.Api.NotFound
                500 -> ErrorType.Api.Server
                503 -> ErrorType.Api.ServiceUnavailable
                else -> ErrorType.Unknown()
            }
        }
        is IOError -> {
            ErrorType.Api.Network
        }

        is UnexpectedCallError -> {
            ErrorType.Unknown()
        }
    }
