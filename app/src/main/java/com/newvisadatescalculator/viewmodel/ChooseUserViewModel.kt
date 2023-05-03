package com.visadatescalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.Graph
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class ChooseUserViewModel(
    private val repository: DataRepository = Graph.dataRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ChooseUserViewState())

    private val refreshing = MutableStateFlow(false)

    val state: StateFlow<ChooseUserViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                repository.getAllPersons(),
                refreshing
            ) { persons, refreshing ->
                ChooseUserViewState(
                    users = persons,
                    refreshing = refreshing,
                    errorMessage = null /* TODO */
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
    val refreshing: Boolean = false,
    val errorMessage: String? = null
)