package com.prapagorn.example.avengers.presenter.heroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prapagorn.example.avengers.presenter.entity.Hero
import kotlinx.android.synthetic.main.vh_hero.view.*

class HeroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(hero: Hero, onClick: ((hero: Hero) -> Unit)?) {
        itemView.apply {
            Glide.with(context)
                .load(hero.imgUrl)
                .into(ivHero)
            tvName.text = hero.name
            tvBio.text = hero.bioShort
            setOnClickListener {
                onClick?.invoke(hero)
            }
        }
    }
}