package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.matchloper.SingleTon
import com.matchloper.data.*
import com.matchloper.network.RetrofitBuilder
import com.matchloper.signUp.SignUpActivity
import com.matchloper.view.MatchActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SignBindingAdapter {

    @BindingAdapter("loginId","pw")
    @JvmStatic
    fun setLogin(button: Button, id : String?, password : String?) {
        button.setOnClickListener {
            val loginData = SignInRequestData(
                SingleTon.prefs.deviceToken.toString(),
                id.toString(),password.toString())

            RetrofitBuilder.networkService.signIn(loginData).enqueue(object :
                Callback<SignInResponseData> {

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

                        val intent = Intent(it.context, MatchActivity::class.java)
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
               userPhone : String?, userPosition : String?, userSkill : String?) {
        button.setOnClickListener {
            val skill = SkillDto(userSkill.toString())
            val position = UserPositionDto("ANDROID")
            val requestBody = SignUpRequestData(
                AddressDto(address.toString(),detailAddress.toString()),userEmail.toString(),"",userName.toString(),userSignUpPw.toString(),userPhone.toString(),
                listOf(skill), listOf(position))

            RetrofitBuilder.networkService.signUp(requestBody).enqueue(object :
                Callback<SignUpResponseData> {
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
                        Toast.makeText(button.context,"회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        (button.context as Activity).finish()
                    }
                }
            })
        }
    }

    @BindingAdapter("setSignUp")
    @JvmStatic
    fun signUp(button: Button, id : String?) {
        button.setOnClickListener {
            val intent = Intent(button.context, SignUpActivity::class.java)
            button.context.startActivity(intent)
        }
    }

}