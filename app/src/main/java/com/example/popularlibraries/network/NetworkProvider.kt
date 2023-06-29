package com.example.popularlibraries.network

import com.example.popularlibraries.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val usersApi: UsersApi by lazy {
        createRetrofit().create(UsersApi::class.java)
    }

    private fun createGson(): Gson = GsonBuilder()
        //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun createClient() = OkHttpClient.Builder().build()

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .build()
}