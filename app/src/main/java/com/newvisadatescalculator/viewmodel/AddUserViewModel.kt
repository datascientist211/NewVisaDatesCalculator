package com.visadatescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    fun addUser(name: String) {
        viewModelScope.launch {
            repository.insertPerson(Person(name))
        }
    }
}
