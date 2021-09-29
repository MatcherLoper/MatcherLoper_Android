package com.matchloper.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnSuccessListener
import com.matchloper.R
import com.matchloper.SingleTon
import com.matchloper.data.DefaultResponseData
import com.matchloper.data.RequestPositionData
import com.matchloper.databinding.FragmentMatchingDialogBinding
import com.matchloper.network.RetrofitBuilder
import com.matchloper.ui.participation.ParticipationViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentMatchingDialogBinding
    private lateinit var viewModel : ParticipationViewModel

    override fun onResume() {
        super.onResume()
        context?.resize(this,0.9f,0.2f)
    }

    private fun Context.resize(dialogFragment: DialogFragment, width : Float, height: Float) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val window = dialogFragment.dialog?.window
        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()
        window?.setLayout(x,y)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isCancelable = false

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_matching_dialog,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this.requireActivity()).get(ParticipationViewModel::class.java)
        binding.viewModel = viewModel

        binding.matchingConfirm.setOnClickListener {
            matchingConfirm()
        }

        binding.matchingCancel.setOnClickListener {
            dialog?.dismiss()
        }

        return binding.root
    }

    private fun matchingConfirm() {
        if(viewModel.position.value != "") {
            val requestBody = RequestPositionData(viewModel.position.value.toString())
            RetrofitBuilder.networkService.joinRoom(requestBody, SingleTon.prefs.userId).enqueue(object :
                Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                }

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()

                    when(res?.message) {
                        null -> Toast.makeText(binding.root.context,"새로운 프로젝트에 참가했습니다", Toast.LENGTH_SHORT).show()
                        "There are no rooms available to participate" -> {
                            Toast.makeText(binding.root.context,"참여할 수 있는 방이 없습니다", Toast.LENGTH_SHORT).show()
                            val notificationChannel = NotificationChannel("10000","test",
                                NotificationManager.IMPORTANCE_DEFAULT)
                            val notificationManager = binding.root.context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            notificationChannel.description = "방 상태 변경"
                            notificationChannel.enableVibration(true)

                            notificationManager.createNotificationChannel(notificationChannel)

                            com.google.firebase.messaging.FirebaseMessaging.getInstance().subscribeToTopic("matching").addOnSuccessListener(
                                OnSuccessListener {
                                    Toast.makeText(binding.root.context,"해당 포지션을 구독했습니다.", Toast.LENGTH_SHORT).show()
                                })
                        }
                        "User can't join room. user role: OWNER" -> Toast.makeText(binding.root.context,"방장은 다른 방에 참여할 수 없습니다.",
                            Toast.LENGTH_SHORT).show()
                        "User can't join room. user role: MATCHING" -> Toast.makeText(binding.root.context,"이미 매칭에 참여하고 있습니다.",
                            Toast.LENGTH_SHORT).show()
                    }
                    dialog?.dismiss()
                }
            })
        }

    }
}