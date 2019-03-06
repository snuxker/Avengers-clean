package com.prapagorn.example.avengers.data.source

import com.prapagorn.example.avengers.data.entity.HeroData
import io.reactivex.Single

interface HeroesDataSource {

    fun getHeroes(): Single<List<HeroData>>
}