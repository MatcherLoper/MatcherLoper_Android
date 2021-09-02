package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.R
import com.matchloper.SingleTon
import com.matchloper.adapter.PositionInfoRecyclerViewAdapter
import com.matchloper.adapter.ProjectRecyclerViewAdapter
import com.matchloper.adapter.TeamMateRecyclerViewAdapter
import com.matchloper.data.*
import com.matchloper.network.RetrofitBuilder
import com.matchloper.signUp.SignUpActivity
import com.matchloper.view.MatchActivity
import com.matchloper.view.MatchOpenDialogActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataBindingUtils {

    @BindingAdapter("loginId","pw")
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
                        Log.e("res",response.body().toString())
                        SingleTon.prefs.userId = response.body()!!.data.id
                        SingleTon.prefs.userToken = response.body()!!.data.authenticationToken

                        val intent = Intent(it.context,MatchActivity::class.java)
                        it.context.startActivity(intent)

                    }

                }

            })

        }
    }

    @BindingAdapter(value = ["address","detailAddress","userName","userEmail","userSignUpPw","userPhone"
    ,"userPosition","userSkill"],requireAll = true)
    @JvmStatic
    fun signUp(button: Button, address : String?, detailAddress: String?, userName : String?, userEmail : String?, userSignUpPw : String?,
    userPhone : String?, userPosition : String? , userSkill : String?) {
        button.setOnClickListener {
            val skill = SkillDto(userSkill.toString())
            val position = UserPositionDto("ANDROID")
            val requestBody = SignUpRequestData(
                AddressDto(address.toString(),detailAddress.toString()),userEmail.toString(),"",userName.toString(),userSignUpPw.toString(),userPhone.toString(),
                listOf(skill), listOf(position))

            RetrofitBuilder.networkService.signUp(requestBody).enqueue(object : Callback<SignUpResponseData>{
                override fun onFailure(call: Call<SignUpResponseData>, t: Throwable) {
                    Log.e("error",t.message)
                }

                override fun onResponse(
                    call: Call<SignUpResponseData>,
                    response: Response<SignUpResponseData>
                ) {
                    val res = response.body()
                    Log.e("test",res.toString())
                    if(res?.message == null) {
                        SingleTon.prefs.userId = res!!.data
                        Toast.makeText(button.context,"회원가입이 완료되었습니다",Toast.LENGTH_SHORT).show()
                        (button.context as Activity).finish()
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

    @BindingAdapter("name", "possibleOfflineArea", "roomPositionList", "userId")
    @JvmStatic
    fun createRoom(button: Button, name : String?, possibleOfflineArea : String?, roomPositionList : ObservableArrayList<RoomPosition>, userId : Int) {
        button.setOnClickListener {
            Log.e("room",roomPositionList.toString())
            val requestBody = RoomCreateRequestData(name.toString(),"서울",roomPositionList,userId)
            RetrofitBuilder.networkService.createRoom(requestBody).enqueue(object : Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {
                    Log.e("error",t.message)
                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()
                    Log.e("ress",response.raw().toString())
                    val message = res?.message
                    Log.e("res",res.toString())
                    if(message == null) (it.context as Activity).finish()
                    else if(message == "user already have another open room") Toast.makeText(button.context,
                    "현재 아이디로 이미 개설된 방이 있습니다",Toast.LENGTH_SHORT).show()

                }
            })
        }
    }

    @BindingAdapter("setSignUp")
    @JvmStatic
    fun signUp(button: Button, id : String?) {
        button.setOnClickListener {
            val intent = Intent(button.context,SignUpActivity::class.java)
            button.context.startActivity(intent)
        }
    }

    @BindingAdapter("roomId", "createdUserId")
    @JvmStatic
    fun delete(button: Button, roomId : Int, createdUserId : Int) {
        if(SingleTon.prefs.userId == createdUserId) {
            button.isClickable = true
            button.setOnClickListener {
                RetrofitBuilder.networkService.deleteRoom(roomId).enqueue(object : Callback<DefaultResponseData> {
                    override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DefaultResponseData>,
                        response: Response<DefaultResponseData>
                    ) {
                        val res = response.body()
                        Log.e("res",res.toString())
                    }
                })
            }
        } else button.isClickable = false
    }

    @BindingAdapter("leaveRoom")
    @JvmStatic
    fun leave(button: Button, roomId : Int) {
        button.setOnClickListener {
            RetrofitBuilder.networkService.leaveRoom(roomId,SingleTon.prefs.userId).enqueue(object : Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()
                    Log.e("res",res.toString())
                }
            })
        }
    }
}