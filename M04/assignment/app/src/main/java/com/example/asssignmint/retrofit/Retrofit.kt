package com.example.asssignmint.retrofit

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
class NetModule {
    val logtag ="NEtModule"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        Log.d(logtag, "prodving retrofit")
        return Retrofit.Builder()
            .baseUrl(AdviceRetriever.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Singleton
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

    @Singleton
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
    fun provideNetworkApi(retrofit: Retrofit): AdviceAPI {
        return retrofit.create(AdviceAPI::class.java)
    }
}
@Component(modules = [NetModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(mewApplication: ApplicationClass)
    fun inject(MainFragment: MainFragment)
}
interface AdviceAPI {

    @GET("advice")
    fun randomAdvice(): Call<AdviceMsg>
}

class AdviceRetriever {
    companion object {
        private const val TAG = "RETRIEVER"
        internal const val BASE_URL = "https://api.adviceslip.com/"
    }



    /*fun getRandomAdvice(): Call<AdviceMsg> {








     //   val adviceAPI = retrofit.create(AdviceAPI::class.java)

       // return adviceAPI.randomAdvice()
    }*/
}
