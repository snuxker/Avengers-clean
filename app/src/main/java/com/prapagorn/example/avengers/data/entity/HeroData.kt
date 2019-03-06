package com.prapagorn.example.avengers.data.entity

import com.google.gson.annotations.SerializedName

data class HeroData(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("short_bio") var bioShort: String?,
    @SerializedName("bio") var bio: String,
    @SerializedName("img_url") var imgUrl: String
)