package com.alexeybondarenko.mvvmtest2.utils.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val githubApi: GithubApi
        get() = retrofit.create(GithubApi::class.java)

    companion object {
        var instance: NetworkService? = null
            get() {
                if (field == null) {
                    field = NetworkService()
                }
                return field
            }
            private set
        private const val BASE_URL = "https://api.github.com/"
    }
}