package org.android.go.sopt.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Support(
    val text: String,
    val url: String
)