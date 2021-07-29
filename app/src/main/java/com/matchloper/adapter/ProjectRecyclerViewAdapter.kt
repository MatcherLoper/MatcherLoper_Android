package com.matchloper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.data.ProjectStateData
import com.matchloper.databinding.ProjectListLayoutBinding

class ProjectRecyclerViewAdapter : RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder>(){

    var items = ObservableArrayList<ProjectStateData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ProjectListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProjectRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : ProjectListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ProjectStateData) {
            binding.projectStateData = item
        }
    }

}