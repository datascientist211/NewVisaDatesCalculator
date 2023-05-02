package com.newvisadatescalculator

import android.app.Application

class NewVisaDatesCalculatorApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}