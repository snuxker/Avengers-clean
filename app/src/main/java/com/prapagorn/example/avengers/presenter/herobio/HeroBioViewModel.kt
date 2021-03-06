package com.prapagorn.example.avengers.presenter.herobio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prapagorn.example.avengers.domain.HeroesUseCase
import com.prapagorn.example.avengers.presenter.entity.HeroBio
import com.prapagorn.example.avengers.util.Event
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HeroBioViewModel(private val heroesUseCase: HeroesUseCase, private val uiSchedulers: Scheduler) : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    private val _showLoadingError = MutableLiveData<Event<Unit>>()
    val showLoadingError: LiveData<Event<Unit>>
        get() = _showLoadingError

    private val _heroBio = MutableLiveData<HeroBio>()
    val heroBio: LiveData<HeroBio>
        get() = _heroBio

    private val disposeBag = CompositeDisposable()

    fun start(heroId: String?) {
        if (!heroId.isNullOrEmpty()) {
            disposeBag.add(heroesUseCase.getHeroBio(heroId)
                .observeOn(uiSchedulers)
                .doOnSubscribe {
                    _showLoading.value = true
                }
                .subscribe({
                    _showLoading.value = false
                    _heroBio.value = it
                },{
                    _showLoading.value = false
                    _showLoadingError.value = Event(Unit)
                }))
        }else {
            _showLoadingError.value = Event(Unit)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}