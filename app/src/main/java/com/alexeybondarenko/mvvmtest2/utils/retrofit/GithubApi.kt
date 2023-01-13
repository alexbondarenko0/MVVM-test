package com.alexeybondarenko.mvvmtest2.utils.retrofit

import retrofit2.Call
import retrofit2.http.*

interface GithubApi {
    @GET("users")
    fun getUsersList(): Call<List<UsersResponse>>
}