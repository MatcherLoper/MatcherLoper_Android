package com.matchloper.util

import android.app.Activity
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.adapter.PositionInfoRecyclerViewAdapter
import com.matchloper.adapter.ProjectRecyclerViewAdapter
import com.matchloper.adapter.TeamMateRecyclerViewAdapter
import com.matchloper.data.ProjectStateData
import com.matchloper.data.RoomPosition
import com.matchloper.data.UserInfo

object DataBindingUtils {

    @BindingAdapter("itemList")
    @JvmStatic
    fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<RoomPosition>){
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = PositionInfoRecyclerViewAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as PositionInfoRecyclerViewAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("closeDialog")
    @JvmStatic
    fun close(button: Button,userId: Int) {
        button.setOnClickListener {
            (it.context as Activity).finish()
        }
    }

    @BindingAdapter("projectList")
    @JvmStatic
    fun bindProject(recyclerView: RecyclerView, items: ObservableArrayList<ProjectStateData>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = ProjectRecyclerViewAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as ProjectRecyclerViewAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("teamMate")
    @JvmStatic
    fun bindTeamMate(recyclerView: RecyclerView, items: ObservableArrayList<UserInfo>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = TeamMateRecyclerViewAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as TeamMateRecyclerViewAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }
}