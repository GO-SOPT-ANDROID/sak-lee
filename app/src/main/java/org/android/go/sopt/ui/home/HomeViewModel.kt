package org.android.go.sopt.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.RequestSignInDto
import org.android.go.sopt.domain.HomeRepository
import org.android.go.sopt.domain.SignRepository
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class HomeViewModel  @Inject constructor(
    private val apiRepository: HomeRepository
) : ViewModel() {
    fun getUserList() = viewModelScope.launch {
        val response = apiRepository.getUserList()
    }
}