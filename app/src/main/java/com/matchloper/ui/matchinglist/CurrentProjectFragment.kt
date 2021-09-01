package com.matchloper.ui.matchinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.FragmentCurrentProjectBinding

class CurrentProjectFragment : Fragment() {

    private lateinit var binding : FragmentCurrentProjectBinding
    private lateinit var viewModel : MatchingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_current_project,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this.requireActivity()).get(MatchingListViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.currentRoomId.value = arguments?.getInt("roomId")

        viewModel.getCurrentProjectInfo()

        return binding.root
    }

}