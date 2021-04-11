package com.example.activities.features.home.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activities.R
import com.example.activities.core.extensions.inflate
import com.example.activities.core.extensions.loadFromUrl
import com.example.activities.core.extensions.toDateView
import com.example.activities.features.home.domain.model.JobPosition
import kotlinx.android.synthetic.main.item_job_row.view.*
import kotlin.properties.Delegates

class JobAdapter
    : RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    internal var collection: List<JobPosition> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (JobPosition) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_job_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jobView: JobPosition, clickListener: (JobPosition) -> Unit) {
            itemView.tvTitle.text = jobView.title
            itemView.tvCompany.text = jobView.company
            itemView.tvDate.text = toDateView(jobView.createdAt)
            jobView.companyLogo?.let { itemView.imgLogo.loadFromUrl(it) }
            itemView.tvType.text = jobView.type
            itemView.tvLocation.text = jobView.location
            itemView.setOnClickListener { clickListener(jobView) }
        }
    }
}