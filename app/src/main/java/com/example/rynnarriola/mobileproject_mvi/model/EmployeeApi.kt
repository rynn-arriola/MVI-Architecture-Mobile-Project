package com.example.rynnarriola.mobileproject_mvi.model

import retrofit2.http.GET

interface EmployeeApi {
    companion object {
        const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"
    }

    @GET("employees.json")
    suspend fun getEmployees(): EmployeeResponse
}