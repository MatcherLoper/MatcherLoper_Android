package com.matchloper.ui.matchinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.FragmentMatchingListBinding

class MatchingListFragment : Fragment() {

    private lateinit var binding : FragmentMatchingListBinding
    private lateinit var matchingListViewModel : MatchingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        matchingListViewModel = ViewModelProvider(this.requireActivity()).get(MatchingListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_matching_list,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = matchingListViewModel

        matchingListViewModel.getRoomList()

        return binding.root
    }
}