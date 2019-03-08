package com.prapagorn.example.avengers.presenter

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prapagorn.example.avengers.LiveDataTestUtil
import com.prapagorn.example.avengers.domain.HeroesUseCase
import com.prapagorn.example.avengers.presenter.entity.HeroBio
import com.prapagorn.example.avengers.presenter.herobio.HeroBioViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class HeroBioViewModelTest {

    lateinit var heroesUseCase: HeroesUseCase

    lateinit var sutViewModel: HeroBioViewModel

    private val mockedHero =  HeroBio(id = "1",
        name = "1",
        imgUrl = "1",
        bioShort = "1",
        bio = "1")

    private val mockedHeroId = "1"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        heroesUseCase = mock(HeroesUseCase::class.java)
        sutViewModel = HeroBioViewModel(heroesUseCase, Schedulers.trampoline())
    }

    @Test
    fun getHeroBio_Success(){
        `when`(heroesUseCase.getHeroBio(mockedHeroId)).thenReturn(Single.just(mockedHero))

        sutViewModel.start(mockedHeroId)

        verify(heroesUseCase).getHeroBio(mockedHeroId)
        verifyNoMoreInteractions(heroesUseCase)
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.showLoading))
        assertEquals(LiveDataTestUtil.getValue(sutViewModel.heroBio), mockedHero)
    }

    @Test
    fun getHeroBio_Fail(){
        `when`(heroesUseCase.getHeroBio(mockedHeroId)).thenReturn(Single.error(NetworkErrorException()))

        sutViewModel.start(mockedHeroId)

        verify(heroesUseCase).getHeroBio(mockedHeroId)
        verifyNoMoreInteractions(heroesUseCase)
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.showLoading))
        val value = LiveDataTestUtil.getValue(sutViewModel.showLoadingError)
        assertNotNull(value.getContentIfNotHandled())
    }

    @Test
    fun getHeroBio_NoId(){
        sutViewModel.start(null)

        val value = LiveDataTestUtil.getValue(sutViewModel.showLoadingError)
        assertNotNull(value.getContentIfNotHandled())
    }
}