package org.android.go.sopt.domain

import org.android.go.sopt.model.RequestSignInDto
import org.android.go.sopt.model.RequestSignUpDto
import org.android.go.sopt.model.ResponseSignUpDto
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface SignRepository {
    suspend fun singUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>
    suspend fun singIn(requestSignInDto: RequestSignInDto): Response<ResponseSignUpDto>
}