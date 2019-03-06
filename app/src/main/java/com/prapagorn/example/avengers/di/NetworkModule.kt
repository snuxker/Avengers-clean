package com.prapagorn.example.avengers.di

import android.content.Context
import com.prapagorn.example.avengers.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

val networkModule = module {
    single { createInterceptor() }
    single { createOkHttpClient(androidContext(), get()) }
    single { createNetworkClient(get(), BuildConfig.BASE_URL) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit
    .Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

private fun createOkHttpClient(context: Context, requestInterceptor: Interceptor): OkHttpClient {
    val cacheDir = File(context.cacheDir, "responses")
    val builder = OkHttpClient.Builder()
        .cache(Cache(cacheDir, 10 * 1024 * 1024)) //10Mb
        .addInterceptor(log(BuildConfig.DEBUG))
        .addInterceptor(requestInterceptor)
    return builder.build()
}

private fun createInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val builder = original.newBuilder()
        .method(original.method(), original.body())
    val newRequest = builder.build()
    chain.proceed(newRequest)
}

private fun log(enabled: Boolean): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (enabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

