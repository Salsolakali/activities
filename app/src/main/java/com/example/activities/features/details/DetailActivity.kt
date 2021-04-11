package com.example.activities.features.details

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.activities.core.extensions.loadFromUrl
import com.example.activities.core.extensions.toDateView
import com.example.activities.core.presentation.Navigator
import com.example.activities.databinding.ActivityDetailBinding
import com.example.activities.features.home.domain.model.JobPosition
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val POSITION_DETAIL_KEY = "position_key"
        fun buildIntent(context: Context, detailBundle: Bundle): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtras(detailBundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI(getDetails())
    }

    private fun getDetails() = intent.extras?.getSerializable(POSITION_DETAIL_KEY) as JobPosition?

    private fun initUI(jobPosition: JobPosition?) {
        binding.imageDetail.loadFromUrl(jobPosition?.companyLogo ?: "")
        binding.tvTitle.text = jobPosition?.title
        binding.tvCompany.text = jobPosition?.company
        binding.tvLocation.text = jobPosition?.location
        binding.tvType.text = jobPosition?.type
        binding.tvDate.text = jobPosition?.createdAt?.let { toDateView(it) }
        binding.tvContentDetail.text =
            Html.fromHtml(jobPosition?.description, Html.FROM_HTML_MODE_LEGACY)

        binding.tvUrl.text = jobPosition?.url
        binding.tvUrl.movementMethod = LinkMovementMethod.getInstance()
        binding.tvUrl.setLinkTextColor(Color.BLUE)
        binding.tvUrl.setOnClickListener {
            jobPosition?.url?.let { it1 ->
                navigator.navigateToWebView(
                    this,
                    it1,
                    this
                )
            }
        }

    }
}