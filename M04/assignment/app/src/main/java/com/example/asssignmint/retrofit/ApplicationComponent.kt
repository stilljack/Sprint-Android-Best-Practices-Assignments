package com.example.asssignmint.retrofit

import android.app.Application
import dagger.android.DaggerApplication_MembersInjector


open class ApplicationClass : Application() {

    companion object {
        lateinit var instance: ApplicationClass
            private set

    lateinit var appComponent: ApplicationComponent
        private set

    }
    override fun onCreate() {
        super.onCreate()
        instance = this
       appComponent= initComponent(this)

    }

    private fun initComponent(app:Application) :ApplicationComponent {
/*        appComponent = .builder()
            .build()*/
     val final=  DaggerApplicationComponent.builder()
            .netModule(NetModule(app))
            .build()
      return final
    }

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