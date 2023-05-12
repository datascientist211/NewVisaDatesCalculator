package com.newvisadatescalculator.di

import android.content.Context
import androidx.room.Room
import com.visadatescalculator.database.MainDatabase
import com.visadatescalculator.model.PersonDao
import com.visadatescalculator.model.TripDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun providePersonDao(database: MainDatabase): PersonDao {
        return database.personDao()
    }

    @Provides
    fun provideTripDao(database: MainDatabase): TripDao {
        return database.tripDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MainDatabase {
        return Room.databaseBuilder(
            appContext,
            MainDatabase::class.java,
            "dates_calculator_database"
        ).build()
    }
}