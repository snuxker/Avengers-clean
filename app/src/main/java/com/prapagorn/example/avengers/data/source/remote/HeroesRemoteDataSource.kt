package com.prapagorn.example.avengers.data.source.remote

import com.prapagorn.example.avengers.data.entity.HeroData
import com.prapagorn.example.avengers.data.source.HeroesDataSource
import io.reactivex.Scheduler
import io.reactivex.Single

class HeroesRemoteDataSource(
    private val heroesApi: HeroesApi,
    private val scheduler: Scheduler
) : HeroesDataSource {

    override fun getHeroes(): Single<List<HeroData>> {
        return heroesApi.getHeroes()
                .subscribeOn(scheduler)
    }
}