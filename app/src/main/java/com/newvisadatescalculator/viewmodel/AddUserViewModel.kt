package com.visadatescalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.Graph
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Person
import kotlinx.coroutines.launch


class AddUserViewModel(
    private val repository: DataRepository = Graph.dataRepository
) : ViewModel() {

    fun addUser(name: String){
        viewModelScope.launch {
            repository.insertPerson(Person(name))
        }
    }
}
