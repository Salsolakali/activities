package com.example.activities.usecase

import app.cash.turbine.test
import com.example.activities.core.domain.ResultOf
import com.example.activities.features.home.domain.repository.DataRepository
import com.example.activities.features.home.domain.useCase.GetJobsUseCase
import com.example.activities.interactors.stubs.JobApiStub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetJobsUseCaseTest {
    private lateinit var sut: GetJobsUseCase

    @Mock
    lateinit var dataRespository: DataRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private fun initInteractor() {
        sut = GetJobsUseCase(dataRespository)
    }

    @Test
    @Throws(Exception::class)
    fun `request repository only once`() {
        sut.execute()
        verify(dataRespository, times(1)).getJobs()
    }

    @Test
    fun `on execute return flow with result`() {
        val remoteObject = JobApiStub.testServiceData
        val flowResult = flowOf(ResultOf.Success(remoteObject))
        whenever(dataRespository.getJobs()).thenReturn(flowResult)

        val result = sut.execute()
        assertEquals(flowResult, result)
    }

    @Test
    fun `on execute return flow with correct object`() {
        val remoteObject = JobApiStub.testServiceData
        val flowResult = flowOf(ResultOf.Success(remoteObject))
        whenever(dataRespository.getJobs()).thenReturn(flowResult)

        runBlocking {
            sut.execute().test {
                assertEquals(ResultOf.Success(remoteObject), expectItem())
                expectComplete()
            }
        }
    }
}