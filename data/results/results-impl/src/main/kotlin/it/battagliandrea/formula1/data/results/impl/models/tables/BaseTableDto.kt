package it.battagliandrea.formula1.data.results.impl.models.tables

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<out T>(
    @SerialName("MRData")
    val mRData: T?,
)

@Serializable
sealed class DataDto {
    abstract val limit: Long?
    abstract val offset: Long?
    abstract val total: Long?
}
