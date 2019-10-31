package com.example.daggerguided

import android.app.Application

class DaggerDemoApplication:Application() {
    val component: DataComponent by lazy {
        DaggerDataComponent
            .builder()
            .dateModule(DateModule(this))
            .build()
    }


    override fun onCreate() {


        super.onCreate()

        component.inject(this)


    }
}