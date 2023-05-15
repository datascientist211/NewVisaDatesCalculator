package com.newvisadatescalculator.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.visadatescalculator.model.Person
import com.visadatescalculator.model.PersonDao
import com.visadatescalculator.model.Trip
import com.visadatescalculator.model.TripDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataRepository @Inject internal constructor(
    private val personDao: PersonDao,
    private val tripDao: TripDao
) {

    @WorkerThread
    suspend fun insertPerson(person: Person) {
        withContext(Dispatchers.IO) {
            personDao.insertPerson(person)
        }
    }

    fun getAllPersons(): Flow<List<Person>> {
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

    fun getTripsByPersonId(personUid: Int): Flow<List<Trip>> {
        return tripDao.getTripsByPersonId(personUid)
    }
}