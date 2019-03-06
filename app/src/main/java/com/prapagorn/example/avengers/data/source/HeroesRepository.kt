package com.prapagorn.example.avengers.data.source

import com.prapagorn.example.avengers.data.entity.HeroData
import com.prapagorn.example.avengers.data.source.remote.HeroesRemoteDataSource
import io.reactivex.Single


class HeroesRepository(private val heroesRemoteDataSource: HeroesRemoteDataSource) : HeroesDataSource {

    override fun getHeroes(): Single<List<HeroData>> {
        return heroesRemoteDataSource.getHeroes()
    }
}