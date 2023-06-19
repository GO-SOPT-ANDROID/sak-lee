package org.android.go.sopt.data.Api

import okhttp3.MultipartBody
import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.data.model.sign.RequestSignUpDto
import org.android.go.sopt.data.model.sign.ResponseSignInDto
import org.android.go.sopt.data.model.sign.ResponseSignUpDto
import org.android.go.sopt.util.API
import org.android.go.sopt.util.API.SIGN_IN
import org.android.go.sopt.util.API.SIGN_UP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignApiService {

    @POST(SIGN_UP)
    suspend fun singUp(@Body requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>

    @POST(SIGN_IN)
    suspend fun singIn(@Body requestSignUpDto: RequestSignInDto): Response<ResponseSignInDto>

    @Multipart
    @POST(API.UPLOAD)
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Response<Unit>
}