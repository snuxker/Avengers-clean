package com.prapagorn.example.avengers.presenter

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prapagorn.example.avengers.LiveDataTestUtil
import com.prapagorn.example.avengers.domain.HeroesUseCase
import com.prapagorn.example.avengers.presenter.entity.Hero
import com.prapagorn.example.avengers.presenter.heroes.HeroesViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class HeroesViewModeTest {

    lateinit var heroesUseCase: HeroesUseCase

    lateinit var sutViewModel: HeroesViewModel

    private val mockedHeroes = listOf(
        Hero(id = "1",
            name = "1",
            imgUrl = "1",
            bioShort = "1"),
        Hero(id = "2",
            name = "2",
            imgUrl = "2",
            bioShort = "2")
    )

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        heroesUseCase = mock(HeroesUseCase::class.java)
        sutViewModel = HeroesViewModel(heroesUseCase, Schedulers.trampoline())
    }

    @Test
    fun getHeroes_Success(){
        `when`(heroesUseCase.getHeroes()).thenReturn(Single.just(mockedHeroes))

        sutViewModel.start()

        verify(heroesUseCase).getHeroes()
        verifyNoMoreInteractions(heroesUseCase)
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.showLoading))
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.heroes).isEmpty())
        assertTrue(LiveDataTestUtil.getValue(sutViewModel.heroes).size == 2)
    }

    @Test
    fun getHeroesEmpty_Success(){
        `when`(heroesUseCase.getHeroes()).thenReturn(Single.just(listOf()))

        sutViewModel.start()

        verify(heroesUseCase).getHeroes()
        verifyNoMoreInteractions(heroesUseCase)
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.showLoading))
        assertTrue(LiveDataTestUtil.getValue(sutViewModel.heroes).isEmpty())
        assertTrue(LiveDataTestUtil.getValue(sutViewModel.showNoHeroes))
    }

    @Test
    fun getHeroes_Fail(){
        `when`(heroesUseCase.getHeroes()).thenReturn(Single.error(NetworkErrorException()))

        sutViewModel.start()

        verify(heroesUseCase).getHeroes()
        verifyNoMoreInteractions(heroesUseCase)
        assertFalse(LiveDataTestUtil.getValue(sutViewModel.showLoading))
        val value = LiveDataTestUtil.getValue(sutViewModel.showLoadingError)
        assertNotNull(value.getContentIfNotHandled())
    }

    @Test
    fun clickHeroItem_NavigateToHeroBio(){
        val mockedHero = mockedHeroes[1]
        sutViewModel.navigateToHeroBio(mockedHero)

        val value = LiveDataTestUtil.getValue(sutViewModel.navigateToHeroBio)
        assertEquals(
            value.getContentIfNotHandled(),
            mockedHero.id
        )
    }
}