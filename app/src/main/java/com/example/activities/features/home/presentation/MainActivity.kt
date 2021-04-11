package com.example.activities.features.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activities.R
import com.example.activities.core.domain.RequestFailure
import com.example.activities.core.extensions.doIfFailure
import com.example.activities.core.extensions.doIfInProgress
import com.example.activities.core.extensions.doIfSuccess
import com.example.activities.core.presentation.Navigator
import com.example.activities.databinding.ActivityMainBinding
import com.example.activities.features.details.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeJobsViewModel

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var jobAdapter: JobAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
        homeViewModel.fetchJobs()
    }

    private fun initUI() {
        setupObservers()
        initRecycler()
        initListeners()
    }

    private fun setupObservers() {
        homeViewModel.jobsResult.observe(this) { result ->
            result.doIfSuccess { items ->
                hideLoading()
                jobAdapter.collection =
                    items.sortedByDescending { it.createdAt } //Entiendo que tienen que mostrarse las más recientes arriba
            }

            result.doIfFailure {
                hideLoading()
                manageError(it)
            }

            result.doIfInProgress {
                showLoading()
            }
        }
    }

    private fun initRecycler() {
        binding.recyclerJobs.layoutManager = LinearLayoutManager(this)
        binding.recyclerJobs.adapter = jobAdapter
    }

    private fun initListeners() {
        jobAdapter.clickListener = { jobPosition ->
            val bundle =
                Bundle().apply { putSerializable(DetailActivity.POSITION_DETAIL_KEY, jobPosition) }
            navigator.navigateToDetail(this, bundle, this)
        }
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun manageError(requestFailure: RequestFailure) {
        val message = when (requestFailure) {
            is RequestFailure.ApiError -> requestFailure.message
            is RequestFailure.NoConnectionError -> getString(R.string.connection_error_message)
            is RequestFailure.UnknownError -> getString(R.string.default_error_message)
        }
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}