package com.example.m02guided.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import io.fabric.sdk.android.services.settings.IconRequest.build
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.Path
import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream


data class Whatever(
        val something:String,
        val yeah:String
)
interface RetrofitInterface {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<*>>

    @GET("repos/{user}/{repo}/contributors")
    fun listRepoContributors(
            @Path("user") user: String,
            @Path("repo") repo: String): Call<List<*>>
}

 class GitHubBasicService {

    private val gitHubApi: RetrofitInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        gitHubApi = retrofit.create(RetrofitInterface::class.java)
    }

    @Throws(IOException::class)
    fun getTopContributors(userName: String): List<String> {
        var repos = gitHubApi
                .listRepos(userName)
                .execute()
                .body()

        repos = if (repos != null) repos else Collections.emptyList()

        return repos.stream()
                .flatMap({ repo -> getContributors(userName, repo) })
                .sorted({ a, b -> b.getContributions() - a.getContributions() })
                .map(Function<Any, Any> { Contributor.getName() })
                .distinct()
                .sorted()
                .collect(Collectors.toList())
    }

    private fun getContributors(userName: String, repo: Repository): Stream<Contributor> {
        var contributors: List<Contributor>? = null
        try {
            contributors = gitHubApi
                    .listRepoContributors(userName, repo.getName())
                    .execute()
                    .body()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        contributors = contributors ?: Collections.emptyList()

        return contributors!!.stream()
                .filter { c -> c.getContributions() > 100 }
    }
}
