package com.newvisadatescalculator

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewVisaDatesCalculatorApp : Application(){
    override fun onCreate() {
        super.onCreate()
        //Graph.provide(this)
    }
}