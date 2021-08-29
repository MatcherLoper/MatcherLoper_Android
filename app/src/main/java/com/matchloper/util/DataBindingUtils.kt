package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.SingleTon
import com.matchloper.adapter.PositionInfoRecyclerViewAdapter
import com.matchloper.adapter.ProjectRecyclerViewAdapter
import com.matchloper.data.*
import com.matchloper.network.RetrofitBuilder
import com.matchloper.view.MatchActivity
import com.matchloper.view.MatchOpenDialogActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataBindingUtils {

    @BindingAdapter("loginOnClick","loginId","pw")
    @JvmStatic
    fun setLogin(button: Button, id : String?, password : String?) {
        button.setOnClickListener {
            val loginData = SignInRequestData(SingleTon.prefs.deviceToken.toString(),
                id.toString(),password.toString())

            RetrofitBuilder.networkService.signIn(loginData).enqueue(object : Callback<SignInResponseData> {

                override fun onFailure(call: Call<SignInResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SignInResponseData>,
                    response: Response<SignInResponseData>
                ) {

                    if(response.isSuccessful) {
                        SingleTon.prefs.userId = response.body()!!.data.id
                        SingleTon.prefs.userToken = response.body()!!.data.authenticationToken

                        val intent = Intent(it.context,MatchActivity::class.java)
                        it.context.startActivity(intent)

                    }

                }

            })

        }
    }

    @BindingAdapter("matchingOpen")
    @JvmStatic
    fun matchingOpen(button : Button, userId : Int) {
        button.setOnClickListener {
            val intent = Intent(button.context,MatchOpenDialogActivity::class.java)
            button.context.startActivity(intent)
        }
    }

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

    @BindingAdapter("name", "possibleOfflineArea", "roomPositionList", "userId")
    @JvmStatic
    fun createRoom(button: Button, name : String?, possibleOfflineArea : String?, roomPositionList : ObservableArrayList<RoomPosition>, userId : Int) {
        button.setOnClickListener {
            val requestBody = RoomCreateRequestData(name.toString(),possibleOfflineArea.toString(),roomPositionList,userId)
            RetrofitBuilder.networkService.createRoom(requestBody).enqueue(object : Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {
                    Log.e("error",t.message)
                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()!!
                    val message = res.message
                    Log.e("res",res.toString())
                    message?.let { str ->
                        (it.context as Activity).finish()
                    }
                }
            })
        }
    }
}