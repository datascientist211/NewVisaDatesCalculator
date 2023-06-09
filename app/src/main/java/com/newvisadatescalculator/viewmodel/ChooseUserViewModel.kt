package com.visadatescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseUserViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ChooseUserViewState())

    val state: StateFlow<ChooseUserViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                repository.getAllPersons(),
                MutableStateFlow(null)
            ) { persons, error ->
                ChooseUserViewState(
                    users = persons,
                    errorMessage = error
                )
            }.catch { throwable ->
                // TODO: emit a UI error here. For now we'll just rethrow
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }
}

data class ChooseUserViewState(
    val users: List<Person> = emptyList(),
    val errorMessage: String? = null
)