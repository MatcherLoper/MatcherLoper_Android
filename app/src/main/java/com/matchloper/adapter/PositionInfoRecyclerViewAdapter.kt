package com.matchloper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.data.RoomPosition
import com.matchloper.databinding.PositionInfoLayoutBinding

class PositionInfoRecyclerViewAdapter : RecyclerView.Adapter<PositionInfoRecyclerViewAdapter.ViewHolder>() {

    var items = ObservableArrayList<RoomPosition> ()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PositionInfoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PositionInfoRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : PositionInfoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RoomPosition) {
            binding.positionInfo = item
            binding.removeButton.setOnClickListener {
                items.remove(item)
            }
        }
    }
}