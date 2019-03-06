package com.prapagorn.example.avengers.domain

import com.prapagorn.example.avengers.data.source.HeroesRepository
import com.prapagorn.example.avengers.presenter.entity.Hero
import com.prapagorn.example.avengers.presenter.entity.HeroBio
import io.reactivex.Single

interface HeroesUseCase {
    fun getHeroes(): Single<List<Hero>>
    fun getHeroBio(heroId: String): Single<HeroBio>
}

class HeroesUseCaseImpl(private val heroesRepository: HeroesRepository): HeroesUseCase {

    override fun getHeroes(): Single<List<Hero>> {
        return heroesRepository.getHeroes()
            .map { heroes ->
            heroes.map { Hero.fromHeroData(it) }
        }
    }

    override fun getHeroBio(heroId: String): Single<HeroBio> {
        return heroesRepository.getHeroes().map { heroes ->
            HeroBio.fromHeroData(heroes.first { it.id == heroId })
        }
    }
}
