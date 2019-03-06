package com.prapagorn.example.avengers.presenter.heroes

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prapagorn.example.avengers.BaseActivity
import com.prapagorn.example.avengers.R
import com.prapagorn.example.avengers.presenter.herobio.HeroBioActivity
import com.prapagorn.example.avengers.util.showSnackBar
import kotlinx.android.synthetic.main.activity_hero.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HeroesActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_hero

    private val viewModel: HeroesViewModel by viewModel()

    private lateinit var heroesAdapter: HeroesAdapter

    override fun setupView() {
        setupList()
        viewModel.heroes.observe(this, Observer {
            if (it != null) {
                heroesAdapter.let { adapter ->
                    adapter.heroList = it
                    adapter.notifyDataSetChanged()
                }
            }
        })
        viewModel.showLoading.observe(this, Observer {
            pbLoading.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
        viewModel.showNoHeroes.observe(this, Observer {
            tvShowNoHeroes.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
        viewModel.showLoadingError.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                clRootView.showSnackBar(getString(R.string.common_loading_error))
            }
        })
        viewModel.navigateToHeroBio.observe(this, Observer {
            it.getContentIfNotHandled()?.let { heroId ->
                val intent = Intent(this, HeroBioActivity::class.java).apply {
                    putExtra(HeroBioActivity.EXTRA_HERO_ID, heroId)
                }
                startActivity(intent)
            }
        })
        viewModel.start()
    }

    private fun setupList() {
        heroesAdapter = HeroesAdapter().apply {
            onClick = {
                viewModel.navigateToHeroBio(it)
            }
        }
        rvHeroes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = heroesAdapter
        }
    }
}
