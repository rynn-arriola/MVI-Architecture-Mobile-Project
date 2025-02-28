package com.example.rynnarriola.mobileproject_mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.mobileproject_mvi.model.Employee
import com.example.rynnarriola.mobileproject_mvi.model.EmployeeRepo
import com.example.rynnarriola.mobileproject_mvi.util.EmployeeIntent
import com.example.rynnarriola.mobileproject_mvi.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repo: EmployeeRepo
):ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Employee>>>(UiState.Success(emptyList()))
    val state:  StateFlow<UiState<List<Employee>>> = _state

    fun onIntent(intent: EmployeeIntent){

        when(intent){
            is EmployeeIntent.LoadEmployees -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repo.getEmployees()
                .flowOn(Dispatchers.IO)
                .catch { e-> _state.value = UiState.Error("Failed: ${e.message}") }
                .collect{employeeList -> _state.value = UiState.Success(employeeList)}
        }
    }
}