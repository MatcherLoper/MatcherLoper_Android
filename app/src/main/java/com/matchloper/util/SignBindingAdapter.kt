package com.matchloper.util

import android.app.Activity
import android.content.Intent
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
                    val res = response.body()

                    if(response.code() == 400) {
                        Toast.makeText(button.context,"로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        when {
                            res?.message.toString().contains("존재하지 않는 사용자입니다") -> Toast.makeText(
                                button.context,
                                "존재하지 않는 사용자입니다",
                                Toast.LENGTH_SHORT
                            ).show()
                            res?.message == "Password is not matched!" -> Toast.makeText(
                                button.context,
                                "비밀번호가 일치하지 않습니다",
                                Toast.LENGTH_SHORT
                            ).show()
                            res?.message == null -> {
                                SingleTon.prefs.userId = res?.data!!.id
                                SingleTon.prefs.userToken = res.data.authenticationToken

                                val intent = Intent(it.context, MatchActivity::class.java)
                                it.context.startActivity(intent)
                            }
                        }

                    }
                }
            })
        }
    }

    @BindingAdapter(value = ["address","detailAddress","userName","userEmail", "userIntroduction", "userSignUpPw","userPhone"
        ,"userPosition","userSkill"],requireAll = true)
    @JvmStatic
    fun signUp(button: Button, address : String?, detailAddress: String?, userName : String?, userEmail : String?, userIntroduction : String?, userSignUpPw : String?,
               userPhone : String?, userPosition : String?, userSkill : String?) {
        button.setOnClickListener {
            val skill = SkillDto(userSkill.toString())
            val position = UserPositionDto(userPosition.toString())
            val requestBody = SignUpRequestData(
                AddressDto(address.toString(),detailAddress.toString()),userEmail.toString(),userIntroduction.toString(),userName.toString(),userSignUpPw.toString(),userPhone.toString(),
                listOf(skill), listOf(position))

            RetrofitBuilder.networkService.signUp(requestBody).enqueue(object :
                Callback<SignUpResponseData> {
                override fun onFailure(call: Call<SignUpResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SignUpResponseData>,
                    response: Response<SignUpResponseData>
                ) {
                    val res = response.body()

                    if(response.code() == 400) {
                        Toast.makeText(button.context,"회원가입에 실패했습니다",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        when {
                            res?.message == null  -> {
                                Toast.makeText(button.context, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                                (button.context as Activity).finish()
                            }
                            res.message.contains("duplicated email") -> {
                                Toast.makeText(button.context, "이미 가입된 이메일입니다", Toast.LENGTH_SHORT).show()
                            }
                        }
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

    @BindingAdapter("signUpCancel")
    @JvmStatic
    fun cancelSignUp(button: Button, id: String?) {
        button.setOnClickListener {
            (button.context as Activity).finish()
        }
    }

}