package com.prapagorn.example.avengers.data.source.remote

import com.prapagorn.example.avengers.data.entity.HeroData
import com.prapagorn.example.avengers.data.source.HeroesDataSource
import com.prapagorn.example.avengers.util.SchedulersFacade
import io.reactivex.Single

class HeroesRemoteDataSource(
    private val heroesApi: HeroesApi,
    private val schedulers: SchedulersFacade
) : HeroesDataSource {

    override fun getHeroes(): Single<List<HeroData>> {
        return heroesApi.getHeroes()
                .subscribeOn(schedulers.io())
    }
}