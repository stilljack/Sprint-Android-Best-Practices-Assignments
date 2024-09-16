package com.example.asssignmint.retrofit

import android.app.Application
import android.util.Log
import com.example.asssignmint.ui.main.MainFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule (app:Application) {
    val logtag ="NEtModule"

    val BASE_URL = "https://api.adviceslip.com/"

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        Log.d(logtag, "prodving retrofit")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providesohHttpClient(): OkHttpClient {
        Log.d(logtag, "prodving okhttp")
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    Log.d(logtag, "logger.level=${logger.level}")

        val ohHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
        return ohHttpClient
    }


    @Provides
    fun providesGson(): Gson {
        return   GsonBuilder()
            .setLenient()
            .create()
    }

    // This function need Retrofit object which we are passing in argument.
    // We will not create Retrofit object in this function.
    // Instead it will be injected/provided by Dagger2.
    // Dagger2 will get Retrofit object from provideRetrofit function declared above.

    @Provides
    fun provideNetworkApi(): AdviceAPI {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        Log.d(logtag, "logger.level=${logger.level}")
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val ohHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
        Log.d(logtag, "prodving retrofit")
       val retrofit=  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(ohHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


        return retrofit.create(AdviceAPI::class.java)
    }
}
@Component(modules = [NetModule::class])
interface ApplicationComponent {
    fun inject(mewApplication: ApplicationClass)
    fun inject(MainFragment: MainFragment)
}
interface AdviceAPI {

    @GET("advice")
    fun randomAdvice(): Call<AdviceMsg>
}




    /*fun getRandomAdvice(): Call<AdviceMsg> {








     //   val adviceAPI = retrofit.create(AdviceAPI::class.java)

       // return adviceAPI.randomAdvice()
    }*/
