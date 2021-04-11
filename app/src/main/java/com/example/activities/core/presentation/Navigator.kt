package com.example.activities.core.presentation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Bundle
import com.example.activities.features.details.DetailActivity.Companion.buildIntent
import com.example.activities.features.details.WebViewActivity
import javax.inject.Inject

class Navigator
@Inject constructor() {

    fun navigateToDetail(context: Context, detailBundle: Bundle, activity: Activity) {
        context.startActivity(
            buildIntent(context, detailBundle),
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
        )
    }

    fun navigateToWebView(context: Context, url: String, activity: Activity) {
        context.startActivity(
            WebViewActivity.buildIntent(context, url),
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
        )
    }


}