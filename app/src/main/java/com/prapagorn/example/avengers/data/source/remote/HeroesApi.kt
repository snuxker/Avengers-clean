package com.prapagorn.example.avengers.data.source.remote

import com.prapagorn.example.avengers.data.entity.HeroData
import io.reactivex.Single
import retrofit2.http.GET

interface HeroesApi {
    @GET("b/5c7b7a240e75295893376830/1")
    fun getHeroes(): Single<List<HeroData>>
}