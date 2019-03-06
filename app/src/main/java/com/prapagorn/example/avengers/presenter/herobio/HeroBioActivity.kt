package com.prapagorn.example.avengers.presenter.herobio

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.prapagorn.example.avengers.BaseActivity
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.presenter.entity.HeroBio
import com.prapagorn.example.avengers.util.showSnackBar
import kotlinx.android.synthetic.main.activity_hero_bio.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroBioActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_hero_bio

    private val viewModel: HeroBioViewModel by viewModel()

    companion object {
        const val EXTRA_HERO_ID = "HERO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showLoading.observe(this, Observer {
            pbLoading.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
        viewModel.showLoadingError.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                nsRootView.showSnackBar(getString(R.string.common_loading_error))
            }
        })
        viewModel.heroBio.observe(this, Observer {
            it.bind()
        })
        viewModel.start(intent.getStringExtra(EXTRA_HERO_ID))
    }

    private fun HeroBio.bind() {
        Glide.with(this@HeroBioActivity)
            .load(this.imgUrl)
            .into(ivHero)
        tvName.text = this.name
        tvBioShort.text = this.bioShort
        tvBio.text = this.bio
    }
}