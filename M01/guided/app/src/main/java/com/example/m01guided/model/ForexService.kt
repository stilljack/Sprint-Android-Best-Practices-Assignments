package com.example.m01guided.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ForexService {
    @GET("{currency")
    fun getRates(@Path("currency") currency:String): Single<Rates>
}