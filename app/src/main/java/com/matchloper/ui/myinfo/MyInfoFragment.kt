package com.matchloper.ui.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.FragmentMyInfoBinding

class MyInfoFragment : Fragment() {

    private lateinit var myInfoViewModel: MyInfoViewModel
    private lateinit var binding : FragmentMyInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_info,container,false)
        myInfoViewModel = ViewModelProvider(this.requireActivity()).get(MyInfoViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = myInfoViewModel

        myInfoViewModel.getMyInfo()

        return binding.root
    }
}