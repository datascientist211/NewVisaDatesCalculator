package com.visadatescalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.newvisadatescalculator.Graph
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Person


class ChooseUserViewModel(
    private val repository: DataRepository = Graph.dataRepository
) : ViewModel() {

    val persons: LiveData<List<Person>> = repository.getAllPersons()
}
