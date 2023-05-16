package com.newvisadatescalculator.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.data.DataRepository
import com.newvisadatescalculator.viewmodel.ErrorType.EXCEPTION
import com.newvisadatescalculator.viewmodel.ErrorType.INTERSECTION
import com.newvisadatescalculator.viewmodel.ErrorType.NO_PERIOD
import com.visadatescalculator.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class AddTripViewModel @Inject constructor(
    private val repository: DataRepository, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Holds a new trip which was just saved
    private val _newTrip = MutableStateFlow<Trip?>(null)

    // Holds a date trip starts
    private val _startDate = MutableStateFlow(DateTime())

    // Holds a date trip finished
    private val _endDate = MutableStateFlow(DateTime())

    // Holds an error
    private val _errorType = MutableStateFlow<ErrorType?>(null)


    private val _state = MutableStateFlow(AddTripViewState(personUid = getPersonId()))

    val state: StateFlow<AddTripViewState>
        get() = _state

    init {
        val personUid = getPersonId()

        viewModelScope.launch {
            combine(
                repository.getTripsByPersonId(personUid),
                _errorType,
                _newTrip,
                _startDate,
                _endDate
            ) { trips, error, newTrip, startDate, endDate ->
                AddTripViewState(
                    trips = trips,
                    newTrip = newTrip,
                    enterDate = startDate,
                    leaveDate = endDate,
                    personUid = getPersonId(),
                    errorType = error
                )
            }.catch { throwable ->
                _state.value = AddTripViewState(
                    personUid = getPersonId(),
                    errorType = EXCEPTION
                )
            }.collect {
                _state.value = it
            }
        }
    }

    private fun getPersonId(): Int {
        return checkNotNull(savedStateHandle.get<Int>("personUid"))
    }

    fun addTrip(enterDate: DateTime?, leaveDate: DateTime?) {
        enterDate?.let { startDate ->
            _startDate.value = startDate
            leaveDate?.let { endDate ->
                _endDate.value = endDate
                if (checkNoIntersection()) {
                    addNewTrip(Trip(startDate, endDate, _state.value.personUid))
                } else {
                    _errorType.value = INTERSECTION
                }
            } ?: run {
                _errorType.value = NO_PERIOD
            }
        } ?: run {
            _errorType.value = NO_PERIOD
        }
    }

    private fun addNewTrip(trip: Trip) {
        viewModelScope.launch {
            repository.insertTrip(trip)
            _newTrip.value = trip
        }
    }

    private fun checkNoIntersection(): Boolean {
        val startDate = _state.value.enterDate
        val endDate = _state.value.leaveDate
        for (trip in _state.value.trips) {
            val correctPeriod =
                (startDate.isBefore(trip.enterDate) && endDate.isBefore(trip.enterDate)) || (startDate.isAfter(
                    trip.leaveDate
                ) && endDate.isAfter(trip.leaveDate))
            if (!correctPeriod) {
                return false
            }
        }
        return true
    }
}

data class AddTripViewState(
    val trips: List<Trip> = emptyList(),
    var newTrip: Trip? = null,
    var enterDate: DateTime = DateTime(),
    var leaveDate: DateTime = DateTime(),
    var personUid: Int,
    var errorType: ErrorType? = null
)


enum class ErrorType {
    INTERSECTION, NO_PERIOD, EXCEPTION
}