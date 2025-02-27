package com.example.rynnarriola.mobileproject_mvi.di

import com.example.rynnarriola.mobileproject_mvi.model.EmployeeApi
import com.example.rynnarriola.mobileproject_mvi.model.EmployeeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEmployeeApi(): EmployeeApi {
        return Retrofit.Builder()
            .baseUrl(EmployeeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(api: EmployeeApi): EmployeeRepo {
        return EmployeeRepo(api)
    }
}