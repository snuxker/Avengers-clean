package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.data.source.remote.HeroesRemoteDataSource
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

val dataSourceModule = module {
    factory { HeroesRemoteDataSource(get(), Schedulers.io()) }
}