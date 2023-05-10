package org.android.go.sopt.data.repoImpl

import org.android.go.sopt.domain.SignRepository
import org.android.go.sopt.data.Api.SignApiService
import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.data.model.sign.RequestSignUpDto
import org.android.go.sopt.data.model.sign.ResponseSignUpDto
import retrofit2.Response
import javax.inject.Inject

class SignRepoImpl @Inject constructor(
    private val apiService: SignApiService
) : SignRepository {

    override suspend fun singUp(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto> =
        apiService.singUp(requestSignUpDto)

    override suspend fun singIn(requestSignInDto: RequestSignInDto): Response<ResponseSignUpDto> =
        apiService.singIn(requestSignInDto)

}