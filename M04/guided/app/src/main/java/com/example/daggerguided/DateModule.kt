package com.example.daggerguided

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DateModule(val app: DaggerDemoApplication) {


    @Provides @Singleton fun provideApp() = app
    @Provides fun provideDateExample():DataExampleContract{

        return Sauce()

    }
}

@Component(modules = [DateModule::class])
interface DataComponent {
    fun inject(app: DaggerDemoApplication)
    fun inject(mainActivity: MainActivity)
}