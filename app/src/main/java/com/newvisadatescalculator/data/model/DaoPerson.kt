package com.visadatescalculator.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT * FROM person where uid = :personId LIMIT 1")
    fun getPersonById(personId: Int): Flow<Person>

    @Insert
    fun insertAll(vararg users: Person)

    @Insert
    fun insertPerson(user: Person)

    @Delete
    fun delete(user: Person)
}