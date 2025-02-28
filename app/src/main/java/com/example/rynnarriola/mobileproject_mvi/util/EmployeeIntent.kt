package com.example.rynnarriola.mobileproject_mvi.util

sealed class EmployeeIntent {
    data object LoadEmployees : EmployeeIntent()
}