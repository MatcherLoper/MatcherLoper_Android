package com.matchloper.ui.participation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnSuccessListener
import com.matchloper.R
import com.matchloper.SingleTon
import com.matchloper.data.DefaultResponseData
import com.matchloper.data.RequestPositionData
import com.matchloper.databinding.FragmentParticipationBinding
import com.matchloper.network.RetrofitBuilder
import com.matchloper.view.MatchingDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipationFragment : Fragment(), MatchingDialogFragment.NoticeDialogListener {

    private lateinit var participationViewModel: ParticipationViewModel
    private lateinit var binding: FragmentParticipationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        participationViewModel = ViewModelProvider(this.requireActivity()).get(ParticipationViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_participation, container, false)
        binding.lifecycleOwner = this
        binding.participationViewModel = participationViewModel

        participationViewModel.getEntry()

        return binding.root
    }

    override fun onDialogCancelClick(dialog: DialogFragment) {
        dialog.dismiss()
    }

    override fun onDialogConfirmClick(dialog: DialogFragment) {
        if(participationViewModel.position.value != "") {
            val requestBody = RequestPositionData(participationViewModel.position.value.toString())
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
                        null -> Toast.makeText(dialog.context,"새로운 프로젝트에 참가했습니다",Toast.LENGTH_SHORT).show()
                        "There are no rooms available to participate" -> {
                            Toast.makeText(dialog.context,"참여할 수 있는 방이 없습니다", Toast.LENGTH_SHORT).show()
                            val notificationChannel = NotificationChannel("10000","test",
                                NotificationManager.IMPORTANCE_DEFAULT)
                            val notificationManager = dialog.context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            notificationChannel.description = "방 상태 변경"
                            notificationChannel.enableVibration(true)

                            notificationManager.createNotificationChannel(notificationChannel)

                            com.google.firebase.messaging.FirebaseMessaging.getInstance().subscribeToTopic("matching").addOnSuccessListener(
                                OnSuccessListener {
                                    Toast.makeText(dialog.context,"해당 포지션을 구독했습니다.", Toast.LENGTH_SHORT).show()
                                })
                        }
                        "User can't join room. user role: OWNER" -> Toast.makeText(dialog.context,"방장은 다른 방에 참여할 수 없습니다.",
                            Toast.LENGTH_SHORT).show()
                        "User can't join room. user role: MATCHING" -> Toast.makeText(dialog.context,"이미 매칭에 참여하고 있습니다.",
                            Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
            })
        }
    }
}