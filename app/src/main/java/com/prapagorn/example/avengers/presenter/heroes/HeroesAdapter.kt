package com.prapagorn.example.avengers.presenter.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.presenter.entity.Hero

class HeroesAdapter: RecyclerView.Adapter<HeroViewHolder>() {

    var heroList = listOf<Hero>()
    var onClick: ((hero: Hero) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_hero, parent, false)
        )

    override fun getItemCount() = heroList.size

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroList[position], onClick)
    }
}