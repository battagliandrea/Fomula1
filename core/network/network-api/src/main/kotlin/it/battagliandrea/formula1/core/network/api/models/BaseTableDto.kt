package it.battagliandrea.formula1.core.network.api.models

import kotlinx.serialization.SerialName

data class BaseResponse<out T>(
    @SerialName("MRData")
    val mRData: T?,
)

abstract class DataDto {
    abstract val limit: Long?
    abstract val offset: Long?
    abstract val total: Long?
}
