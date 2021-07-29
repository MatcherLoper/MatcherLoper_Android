package com.matchloper.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matchloper.R
import com.matchloper.databinding.ActivityMatchOpenDialogBinding
import com.matchloper.ui.participation.HomeViewModel

class MatchOpenDialogActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityMatchOpenDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_match_open_dialog)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }
}