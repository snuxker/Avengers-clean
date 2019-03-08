package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.domain.HeroesUseCase
import com.prapagorn.example.avengers.domain.HeroesUseCaseImpl
import org.koin.dsl.module.module

val useCaseModule = module {
    factory<HeroesUseCase> { HeroesUseCaseImpl(get()) }
}