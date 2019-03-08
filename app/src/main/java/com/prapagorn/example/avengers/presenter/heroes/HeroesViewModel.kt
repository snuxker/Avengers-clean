package com.prapagorn.example.avengers.presenter.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prapagorn.example.avengers.domain.HeroesUseCase
import com.prapagorn.example.avengers.presenter.entity.Hero
import com.prapagorn.example.avengers.util.Event
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HeroesViewModel(private val useCase: HeroesUseCase, private val uiSchedulers: Scheduler) : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    private val _showNoHeroes = MutableLiveData<Boolean>()
    val showNoHeroes: LiveData<Boolean>
        get() = _showNoHeroes

    private val _showLoadingError = MutableLiveData<Event<Unit>>()
    val showLoadingError: LiveData<Event<Unit>>
        get() = _showLoadingError

    private val _heroes = MutableLiveData<List<Hero>>().apply { value = emptyList() }
    val heroes: LiveData<List<Hero>>
        get() = _heroes

    private val _navigateToHeroBio = MutableLiveData<Event<String>>()
    val navigateToHeroBio: LiveData<Event<String>>
        get() = _navigateToHeroBio

    private val disposeBag = CompositeDisposable()

    internal fun navigateToHeroBio(hero: Hero) {
        _navigateToHeroBio.value = Event(hero.id)
    }

    fun start() {
        getHeroes()
    }

    private fun getHeroes() {
        disposeBag.add(useCase.getHeroes()
            .observeOn(uiSchedulers)
            .doOnSubscribe {
                _showLoading.value = true
            }
            .doOnError {
                _showLoading.value = false
                listOf<Hero>()
            }
            .subscribe({
                _showLoading.value = false
                _heroes.value = it
            }, {
                _showLoadingError.value = Event(Unit)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}