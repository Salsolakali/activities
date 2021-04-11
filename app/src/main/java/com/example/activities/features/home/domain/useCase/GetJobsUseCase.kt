package com.example.activities.features.home.domain.useCase

import com.example.activities.features.home.domain.repository.DataRepository
import javax.inject.Inject

class GetJobsUseCase
@Inject constructor(private val repository: DataRepository) {
    fun execute() = repository.getJobs()
}