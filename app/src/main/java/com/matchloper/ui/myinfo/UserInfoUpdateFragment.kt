package com.matchloper.ui.myinfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.data.UserInfoData
import com.matchloper.databinding.FragmentUserInfoUpdateBinding

class UserInfoUpdateFragment : Fragment() {

    private lateinit var binding : FragmentUserInfoUpdateBinding
    private lateinit var viewModel : MyInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_info_update,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this.requireActivity()).get(MyInfoViewModel::class.java)
        binding.viewModel = viewModel

        val arg = arguments?.getParcelable<UserInfoData>("userInfo")

        return binding.root
    }
}