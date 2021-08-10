package com.matchloper.ui.matchinglist

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.data.ProjectStateData

class MatchingListViewModel : ViewModel() {

    val projectState = ObservableArrayList<ProjectStateData>()
}