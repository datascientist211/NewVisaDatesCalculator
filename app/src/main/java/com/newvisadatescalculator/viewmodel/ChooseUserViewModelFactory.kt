package com.visadatescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newvisadatescalculator.Graph

class ChooseUserViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseUserViewModel::class.java)) {
            return ChooseUserViewModel(Graph.dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}