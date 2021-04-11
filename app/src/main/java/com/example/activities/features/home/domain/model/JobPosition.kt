package com.example.activities.features.home.domain.model

import java.io.Serializable
import java.util.*

data class JobPosition(
    val id: String?,
    val type: String?,
    val url: String?,
    val createdAt: Date,
    val company: String?,
    val companyUrl: String?,
    val location: String?,
    val title: String?,
    val description: String?,
    val howToApply: String?,
    val companyLogo: String?,
) : Serializable {
}