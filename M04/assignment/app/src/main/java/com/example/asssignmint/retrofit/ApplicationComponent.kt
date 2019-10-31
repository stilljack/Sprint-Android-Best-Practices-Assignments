package com.example.asssignmint.retrofit

import android.app.Application

open class ApplicationClass : Application() {


    public lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        // ApplicationComponent is our component interface.
        // NetModule is our Module class.

        applicationComponent = DaggerApplicationComponent.builder()
            .netModule(NetModule())
            .build()

        applicationComponent.inject(this)
    }
}