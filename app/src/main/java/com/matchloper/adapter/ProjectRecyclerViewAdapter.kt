package com.matchloper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ObservableArrayList
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.R
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

            binding.root.setOnClickListener {
                it.findNavController().navigate(R.id.action_navigation_matching_list_to_navigation_current_project,
                    bundleOf("roomId" to item.roomId))
            }
        }
    }

}