package com.matchloper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.FragmentMatchingDialogBinding
import com.matchloper.ui.participation.ParticipationViewModel

class MatchingDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentMatchingDialogBinding
    private lateinit var viewModel : ParticipationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_matching_dialog,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this.requireActivity()).get(ParticipationViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}