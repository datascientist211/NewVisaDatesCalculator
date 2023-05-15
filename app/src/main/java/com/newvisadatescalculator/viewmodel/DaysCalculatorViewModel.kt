package com.visadatescalculator.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaysCalculatorViewModel @Inject constructor(
    private val repository: DataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DaysCalculatorViewState())

    val state: StateFlow<DaysCalculatorViewState>
        get() = _state

    init {
        val personUid = checkNotNull(savedStateHandle.get<Int>("personUid"))

        viewModelScope.launch {
            combine(
                repository.getTripsByPersonId(personUid),
                MutableStateFlow(null)
            ) { trips, error ->
                DaysCalculatorViewState(
                    trips = trips,
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

data class DaysCalculatorViewState(
    val trips: List<Trip> = emptyList(),
    val errorMessage: String? = null,
    val resultTripDays: Int? = null,
)