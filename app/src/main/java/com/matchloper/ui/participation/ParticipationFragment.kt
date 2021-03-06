package com.matchloper.ui.participation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.FragmentParticipationBinding

class ParticipationFragment : Fragment() {

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

}