package com.example.activities.features.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activities.core.domain.ResultOf
import com.example.activities.features.home.domain.model.JobPosition
import com.example.activities.features.home.domain.useCase.GetJobsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeJobsViewModel
@Inject constructor(private val getJobsUseCase: GetJobsUseCase) : ViewModel() {
    var jobsResult: MutableLiveData<ResultOf<List<JobPosition>>> = MutableLiveData()

    fun fetchJobs() {
        viewModelScope.launch {
            getJobsUseCase.execute()
                .onStart { emit(ResultOf.InProgress) }
                .collect { jobsResult.postValue(it) }
        }
    }
}