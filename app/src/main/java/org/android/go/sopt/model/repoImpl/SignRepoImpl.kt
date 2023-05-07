package org.android.go.sopt.model.repoImpl

import org.android.go.sopt.domain.SignRepository
import org.android.go.sopt.model.Api.SignApiService
import org.android.go.sopt.model.RequestSignInDto
import org.android.go.sopt.model.RequestSignUpDto
import org.android.go.sopt.model.ResponseSignUpDto
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