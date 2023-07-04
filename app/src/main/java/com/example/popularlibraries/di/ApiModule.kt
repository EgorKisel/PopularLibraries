package com.example.popularlibraries.di

import android.net.ConnectivityManager
import com.example.popularlibraries.BuildConfig
import com.example.popularlibraries.core.ConnectivityListener
import com.example.popularlibraries.model.network.UsersApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object ApiModule {

    @Named("baseUrl")
    @Provides
    fun provideBaseUrl(): String = BuildConfig.SERVER_URL

    @Provides
    fun provideApi(@Named("baseUrl") baseUrl: String, gson: Gson, client: OkHttpClient): UsersApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UsersApi::class.java)

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun client() = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val url = request.url.newBuilder().build()
        chain.proceed(request.newBuilder().url(url).build())
    }.build()

//    @Singleton
//    @Provides
//    fun provideNetworkStatus(context: Context): INetworkStatus =
//        AndroidNetworkStatus(context)

    @Singleton
    @Provides
    fun provideConnectivityListener(connectivityManager: ConnectivityManager): ConnectivityListener =
        ConnectivityListener(connectivityManager)
}