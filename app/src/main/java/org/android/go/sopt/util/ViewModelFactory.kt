package org.android.go.sopt.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            // SignUp
//            modelClass.isAssignableFrom(JoinViewModel::class.java) -> {
//                JoinViewModel() as T
//            }
//            else -> {
//                throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
//            }
//        }
//    }
}