package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.data.source.remote.HeroesApi
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    factory { createHeroesApi(get()) }
}

private fun createHeroesApi(retrofit: Retrofit) =
    retrofit.create(HeroesApi::class.java)
