package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.data.source.HeroesRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { HeroesRepository(get()) }
}