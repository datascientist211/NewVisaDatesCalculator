package com.newvisadatescalculator

import android.content.Context
import androidx.room.Room
import com.newvisadatescalculator.data.DataRepository
import com.visadatescalculator.database.MainDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A very simple global singleton dependency graph.
 *
 * For a real app, you would use something like Hilt/Dagger instead.
 */
object Graph {

    private lateinit var database: MainDatabase

    val dataRepository by lazy {
        DataRepository(
            personDao = database.personDao(),
            tripDao = database.tripDao()
        )
    }

    private val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    private val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, MainDatabase::class.java, "dates_calculator_database").build()
    }
}
