package com.prapagorn.example.avengers.presenter.entity

import com.prapagorn.example.avengers.data.entity.HeroData

data class Hero(
    var id: String,
    var name: String,
    var bioShort: String,
    var imgUrl: String
) {
    companion object {

        fun fromHeroData(heroData: HeroData) = Hero(
            id = heroData.id,
            name = heroData.name,
            bioShort = heroData.bioShort ?: heroData.bio,
            imgUrl = heroData.imgUrl
        )
    }
}
