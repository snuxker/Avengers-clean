package com.prapagorn.example.avengers.di

import com.prapagorn.example.avengers.util.SchedulersFacade
import org.koin.dsl.module.module

val utilityModule = module {
    factory { SchedulersFacade() }
}