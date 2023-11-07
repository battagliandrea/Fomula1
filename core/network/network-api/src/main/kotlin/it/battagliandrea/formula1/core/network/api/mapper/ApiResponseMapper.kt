package it.battagliandrea.formula1.core.network.api.mapper

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.skydoves.sandwich.retrofit.statusCode

object ApiResponseMapper : ApiErrorModelMapper<String> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error): String =
        "${apiErrorResponse.statusCode.code}, ${apiErrorResponse.message()}"
}
