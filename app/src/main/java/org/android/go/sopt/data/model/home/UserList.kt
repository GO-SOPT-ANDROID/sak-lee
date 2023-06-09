package org.android.go.sopt.data.model.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserList(
    @SerialName("data") val data: List<ResponseUserInfo>,
    @SerialName("page") val page: Int,
    @SerialName("per_page") val per_page: Int,
    @SerialName("support") val support: Support,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val total_pages: Int
)