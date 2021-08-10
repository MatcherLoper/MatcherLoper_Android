package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.adapter.PositionInfoRecyclerViewAdapter
import com.matchloper.adapter.ProjectRecyclerViewAdapter
import com.matchloper.data.PositionInfoData
import com.matchloper.data.ProjectStateData
import com.matchloper.view.MatchOpenDialogActivity

object DataBindingUtils {

    @BindingAdapter("matchingOpen")
    @JvmStatic
    fun matchingOpen(button : Button, id : Int) {
        button.setOnClickListener {
            val intent = Intent(button.context,MatchOpenDialogActivity::class.java)
            button.context.startActivity(intent)
        }
    }

    @BindingAdapter("itemList")
    @JvmStatic
    fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<PositionInfoData>){
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
    fun close(button: Button, id: Int) {
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
}