package com.matchloper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.data.UserInfo
import com.matchloper.databinding.TeamMateItemLayoutBinding

class TeamMateRecyclerViewAdapter : RecyclerView.Adapter<TeamMateRecyclerViewAdapter.ViewHolder>(){
    var items = ArrayList<UserInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = TeamMateItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamMateRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding : TeamMateItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserInfo) {
            binding.item = item
        }
    }
}