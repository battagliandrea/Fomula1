package it.battagliandrea.formula1.core.network.api.extensions

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.statusCode
import it.battagliandrea.formula1.core.resource.ErrorType

fun ApiResponse.Failure.Error.toErrorType(): ErrorType =
    when (this.statusCode.code) {
        404 -> ErrorType.Api.NotFound
        500 -> ErrorType.Api.Server
        503 -> ErrorType.Api.ServiceUnavailable
        else -> ErrorType.Unknown
    }
