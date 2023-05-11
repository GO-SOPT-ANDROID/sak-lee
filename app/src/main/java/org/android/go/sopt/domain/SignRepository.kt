package org.android.go.sopt.domain

import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.data.model.sign.RequestSignUpDto
import org.android.go.sopt.data.model.sign.ResponseSignInDto
import org.android.go.sopt.data.model.sign.ResponseSignUpDto
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface SignRepository {
    suspend fun singUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>
    suspend fun singIn(requestSignInDto: RequestSignInDto): Response<ResponseSignInDto>
}