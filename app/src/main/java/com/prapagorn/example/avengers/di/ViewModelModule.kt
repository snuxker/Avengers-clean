package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.presenter.herobio.HeroBioViewModel
import com.prapagorn.example.avengers.presenter.heroes.HeroesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { HeroesViewModel(get(), get()) }
    viewModel { HeroBioViewModel(get(), get()) }
}