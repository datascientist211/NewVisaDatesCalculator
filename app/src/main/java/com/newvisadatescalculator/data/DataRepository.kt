package com.newvisadatescalculator.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.visadatescalculator.database.MainDatabase
import com.visadatescalculator.model.Person
import com.visadatescalculator.model.PersonDao
import com.visadatescalculator.model.Trip
import com.visadatescalculator.model.TripDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository internal constructor(
    private val personDao: PersonDao,
    private val tripDao: TripDao

    ) {

    @WorkerThread
    suspend fun insertPerson(person: Person) {
        withContext(Dispatchers.IO) {
            personDao.insertPerson(person)
        }
    }

    fun getAllPersons(): LiveData<List<Person>> {
        return personDao.getAll()
    }

    @WorkerThread
    suspend fun insertTrip(trip: Trip) {
        withContext(Dispatchers.IO) {
            tripDao.insertTrip(trip)
        }
    }

    @WorkerThread
    suspend fun deleteTrip(trip: Trip) {
        withContext(Dispatchers.IO) {
            tripDao.delete(trip)
        }
    }

    fun getAllTrips(): LiveData<List<Trip>> {
        return tripDao.getAll()
    }

    fun getTripsByPersonId(personId: Int): LiveData<List<Trip>> {
        return tripDao.getTripsByPersonId(personId)
    }
}