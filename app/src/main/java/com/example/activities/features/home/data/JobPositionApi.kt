package com.example.activities.features.home.data

import com.example.activities.core.extensions.toDate
import com.example.activities.features.home.domain.model.JobPosition
import com.google.gson.annotations.SerializedName

data class JobPositionApi(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String,
    @SerializedName("created_at") val created_at: String = "",
    @SerializedName("company") val company: String,
    @SerializedName("company_url") val company_url: String?,
    @SerializedName("location") val location: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("how_to_apply") val how_to_apply: String,
    @SerializedName("company_logo") val company_logo: String?
) {
    fun toDomain(): JobPosition {
        return JobPosition(
            id,
            type,
            url,
            created_at.toDate(created_at),
            company,
            company_url,
            location,
            title,
            description,
            how_to_apply,
            company_logo
        )
    }
}