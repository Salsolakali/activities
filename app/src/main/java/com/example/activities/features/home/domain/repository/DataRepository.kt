package com.example.activities.features.home.domain.repository

import com.example.activities.core.domain.ResultOf
import com.example.activities.features.home.domain.model.JobPosition
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getJobs(): Flow<ResultOf<List<JobPosition>>>
}