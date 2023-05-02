package com.visadatescalculator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.visadatescalculator.model.Person
import com.visadatescalculator.model.PersonDao
import com.visadatescalculator.model.Trip
import com.visadatescalculator.model.TripDao


@Database(entities = [Person::class, Trip::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    abstract fun tripDao(): TripDao
}