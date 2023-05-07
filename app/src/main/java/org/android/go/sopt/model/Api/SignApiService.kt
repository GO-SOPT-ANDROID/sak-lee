package org.android.go.sopt.model.Api

import org.android.go.sopt.model.RequestSignInDto
import org.android.go.sopt.model.RequestSignUpDto
import org.android.go.sopt.model.ResponseSignUpDto
import org.android.go.sopt.util.API.SIGN_IN
import org.android.go.sopt.util.API.SIGN_UP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApiService {

    @POST(SIGN_UP)
    suspend fun singUp(@Body requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>
    @POST(SIGN_IN)
    suspend fun singIn(@Body requestSignUpDto: RequestSignInDto): Response<ResponseSignUpDto>
}