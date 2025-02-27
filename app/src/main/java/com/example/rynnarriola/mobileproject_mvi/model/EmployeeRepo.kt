package com.example.rynnarriola.mobileproject_mvi.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmployeeRepo @Inject constructor(
    private val employeeApi: EmployeeApi
) {
    fun getEmployees(): Flow<List<Employee>> {
        return flow {
            emit(employeeApi.getEmployees())
        }.map {
            it.employees
        }
    }
}