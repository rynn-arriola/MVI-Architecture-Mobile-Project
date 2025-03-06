package com.example.rynnarriola.mobileproject_mvi

import com.example.rynnarriola.mobileproject_mvi.model.Employee
import com.example.rynnarriola.mobileproject_mvi.model.EmployeeRepo
import com.example.rynnarriola.mobileproject_mvi.util.EmployeeIntent
import com.example.rynnarriola.mobileproject_mvi.util.UiState
import com.example.rynnarriola.mobileproject_mvi.viewmodel.EmployeeViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class EmployeeViewModelTest {

    @RelaxedMockK
    private lateinit var repo: EmployeeRepo

    private lateinit var viewModel: EmployeeViewModel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = EmployeeViewModel(repo)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Reset coroutine dispatcher
    }

    @Test
    fun `fetchData() should emit Loading, then Success state with employee list`() = runTest {
        // Mock repository response
        val mockEmployeeList = getEmployeeListStub()
        every { repo.getEmployees() } returns flowOf(mockEmployeeList)

        // Trigger the function
        viewModel.onIntent(EmployeeIntent.LoadEmployees)

        advanceUntilIdle()  // Wait for coroutines to complete
        assertEquals(UiState.Success(mockEmployeeList), viewModel.state.value)  // Check Success state
        verify(exactly = 1) {repo.getEmployees()}
    }

    private fun getEmployeeListStub(): List<Employee> = listOf(
        Employee(
            biography = "Experienced Android Developer",
            emailAddress = "john.doe@example.com",
            employeeType = "FULL_TIME",
            fullName = "John Doe",
            phoneNumber = "123-456-7890",
            photoUrlLarge = "https://example.com/photo_large.jpg",
            photoUrlSmall = "https://example.com/photo_small.jpg",
            team = "Mobile Team",
            uuid = "123e4567-e89b-12d3-a456-426614174000"
        ),
        Employee(
            biography = "Backend Engineer",
            emailAddress = "jane.smith@example.com",
            employeeType = "CONTRACTOR",
            fullName = "Jane Smith",
            phoneNumber = "987-654-3210",
            photoUrlLarge = "https://example.com/photo_large2.jpg",
            photoUrlSmall = "https://example.com/photo_small2.jpg",
            team = "Backend Team",
            uuid = "987e6543-a21b-56d7-b890-123456789abc"
        ),
        Employee(
            biography = "UX Designer",
            emailAddress = "alex.jones@example.com",
            employeeType = "PART_TIME",
            fullName = "Alex Jones",
            phoneNumber = "555-123-4567",
            photoUrlLarge = "https://example.com/photo_large3.jpg",
            photoUrlSmall = "https://example.com/photo_small3.jpg",
            team = "Design Team",
            uuid = "567fgh89-4321-abcd-9876-abcdef123456"
        )
    )


}