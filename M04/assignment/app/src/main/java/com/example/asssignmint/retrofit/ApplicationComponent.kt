package com.example.asssignmint.retrofit

import android.app.Application

open class ApplicationClass : Application() {

    companion object {
        lateinit var instance: ApplicationClass
            private set
    }

    lateinit var appComponent: ApplicationComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerApplicationComponent.builder()
            .build()
        appComponent.inject(this)
    }
    public lateinit var applicationComponent: ApplicationComponent

  /*  override fun onCreate() {
        super.onCreate()

        // ApplicationComponent is our component interface.
        // NetModule is our Module class.

        applicationComponent = DaggerApplicationComponent.builder()
            .netModule(NetModule())
            .build()

        applicationComponent.inject(this)
    }*/
}