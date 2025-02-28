package com.example.rynnarriola.mobileproject_mvi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import com.example.rynnarriola.mobileproject_mvi.model.Employee
import com.example.rynnarriola.mobileproject_mvi.util.EmployeeIntent
import com.example.rynnarriola.mobileproject_mvi.util.UiState
import com.example.rynnarriola.mobileproject_mvi.viewmodel.EmployeeViewModel

@Composable
fun EmployeeListScreen(viewModel: EmployeeViewModel = hiltViewModel()) {
    // Collect UI state from ViewModel
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()
        .padding(16.dp)
        .statusBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        Button(
            onClick = { viewModel.onIntent(EmployeeIntent.LoadEmployees) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Load Employees")
        }

        // Handle Different States
        when (state) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp))
            }
            is UiState.Success -> {
                val employees = (state as UiState.Success<List<Employee>>).data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(employees) { employee ->
                        EmployeeItem(employee = employee)
                    }
                }
            }
            is UiState.Error -> {
                Text(text = (state as UiState.Error).message, color = androidx.compose.ui.graphics.Color.Red)
            }
        }
    }
}

// Composable function for individual employee items
@Composable
fun EmployeeItem(employee: Employee) {
    Column(modifier = Modifier.padding(8.dp)) {
        // Using Coil to load images from URLs
        val painter = rememberImagePainter(employee.photoUrlLarge)
        Image(
            painter = painter,
            contentDescription = employee.fullName,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = employee.fullName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}