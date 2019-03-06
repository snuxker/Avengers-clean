package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.data.source.remote.HeroesRemoteDataSource
import org.koin.dsl.module.module

val dataSourceModule = module {
    factory { HeroesRemoteDataSource(get(), get()) }
}