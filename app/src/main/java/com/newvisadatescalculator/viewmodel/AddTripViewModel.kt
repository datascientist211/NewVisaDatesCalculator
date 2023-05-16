package com.newvisadatescalculator.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newvisadatescalculator.data.DataRepository
import com.newvisadatescalculator.viewmodel.ErrorType.END_BEFORE_START
import com.newvisadatescalculator.viewmodel.ErrorType.EXCEPTION
import com.newvisadatescalculator.viewmodel.ErrorType.INTERSECTION
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

    private val _state = MutableStateFlow(AddTripViewState(personUid = getPersonId()))

    val state: StateFlow<AddTripViewState>
        get() = _state

    init {
        val personUid = getPersonId()

        viewModelScope.launch {
            combine(
                MutableStateFlow(personUid),
                repository.getTripsByPersonId(personUid)
            ) { personUid, trips ->
                AddTripViewState(
                    trips = trips,
                    personUid = personUid
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

    fun addTrip() {
        viewModelScope.launch {
            val enterDate = _state.value.enterDate
            val leaveDate = _state.value.leaveDate
            if (enterDate.isBefore(leaveDate)) {
                if (isPeriodsCorrect()) {
                    val trip = Trip(enterDate, leaveDate, _state.value.personUid)
                    repository.insertTrip(trip)
                    _state.value.newTrip = trip
                } else {
                    _state.value.errorType = INTERSECTION
                    //"This trip intersects other trip"
                }
            } else {
                _state.value.errorType = END_BEFORE_START
                //"The date of entrance is later than date of leaving schengen area"
            }
        }
    }

    private fun isPeriodsCorrect(): Boolean {
        val startDate = _state.value.enterDate
        val endDate = _state.value.leaveDate
        for (trip in _state.value.trips) {
            val correctPeriod = (startDate.isBefore(trip.enterDate) && endDate.isBefore(trip.enterDate)) ||
                        (startDate.isAfter(trip.leaveDate) && endDate.isAfter(trip.leaveDate))
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
    var errorType: ErrorType? = null,
    val now: DateTime = DateTime()
)


enum class ErrorType {
    INTERSECTION,
    END_BEFORE_START,
    EXCEPTION
}