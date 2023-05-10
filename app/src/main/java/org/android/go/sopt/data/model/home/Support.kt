package org.android.go.sopt.data.model.home

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val text: String,
    val url: String
)