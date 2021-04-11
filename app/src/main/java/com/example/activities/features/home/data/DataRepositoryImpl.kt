package com.example.activities.features.home.data

import com.example.activities.core.data.APIService
import com.example.activities.core.data.FailureFactory
import com.example.activities.core.domain.ResultOf
import com.example.activities.core.extensions.fiveDaysBefore
import com.example.activities.core.extensions.safeCall
import com.example.activities.features.home.domain.model.JobPosition
import com.example.activities.features.home.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DataRepositoryImpl(private val apiService: APIService) : DataRepository {
    override fun getJobs(): Flow<ResultOf<List<JobPosition>>> = flow {
        emit(
            apiService.getJobs().safeCall({ response ->
                response.map { it.toDomain() }
                    .filter { it.createdAt.after(fiveDaysBefore()) } //Filtro las ofertas de los ultimos 5 dias
            })
        )
    }.catch {
        emit(FailureFactory().handleException(it))
    }
}