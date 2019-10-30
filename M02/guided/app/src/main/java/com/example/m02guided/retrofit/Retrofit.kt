package com.example.m02guided.retrofit

import io.reactivex.Observable
import retrofit2.http.GET


data class Whatever(
        val something:String,
        val yeah:String
)
interface RetrofitInterface {


        @GET("api/android")
        fun getData() : Observable<List<Whatever>>
    }

}

object Retrofit {

    a single tear falls streams down bil


}