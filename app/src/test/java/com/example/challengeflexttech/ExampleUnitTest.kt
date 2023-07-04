package com.example.challengeflexttech

import com.example.challengeflexttech.model.Cat
import com.example.challengeflexttech.model.HomeViewModel
import com.example.challengeflexttech.repository.Repository
import com.example.challengeflexttech.utils.getStatus
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `getCats should emit Loading and Success status`() = runBlocking {
        // Arrange
        val cats = listOf(Cat(mock()), Cat(id = "2"))
        `when`(repository.getCat()).thenReturn(cats)

        // Act
        val result = viewModel.getCats()

        // Assert
        assertThat(result.size, equalTo(2))
        assertThat(result[0], instanceOf(getStatus.Loading::class.java))
        assertThat(result[1], instanceOf(getStatus.Succes::class.java))
        val successResult = result[1] as getStatus.Succes<*>
        assertThat(successResult.data, equalTo(cats))
    }

    @Test
    fun `getCats should emit Loading and Failure status`() = runBlocking {
        // Arrange
        val exception = Exception("Something went wrong")
        `when`(repository.getCat()).thenThrow(exception)

        // Act
        val result = viewModel.getCats()

        // Assert
        assertThat(result.size, equalTo(2))
        assertThat(result[0], instanceOf(getStatus.Loading::class.java))
        assertThat(result[1], instanceOf(getStatus.Failure::class.java))
        val failureResult = result[1] as getStatus.Failure
        assertThat(failureResult.exception, equalTo(exception))
    }
}
}