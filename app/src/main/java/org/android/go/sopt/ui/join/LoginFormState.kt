package org.android.go.sopt.ui.join

data class LoginFormState(
    val idError: String? = null,
    val pwError: String? = null,
    val pwCheckError: String? = null,
    val isDataValid: Boolean = false
)
