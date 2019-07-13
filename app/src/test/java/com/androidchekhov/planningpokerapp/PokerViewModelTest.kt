package com.androidchekhov.planningpokerapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokerViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokerViewModel

    @Before
    fun setUp() {
        viewModel = PokerViewModel()
    }

    @Test
    fun `no input should return start state`() {
        // assert
        viewModel.viewState.observeForever {
            assert(it is Start)
        }
    }

    @Test
    fun `reset should return start state`() {
        // act
        viewModel.onReset()

        // assert
        viewModel.viewState.observeForever {
            assert(it is Start)
        }
    }

    @Test
    fun `on card tap should return show estimate front`() {
        // arrange
        viewModel.onSelect(3)

        // act
        viewModel.onCardTap()

        // assert
        viewModel.viewState.observeForever {
            require(it is ShowEstimateFront)
            assertEquals(3, it.estimate)
        }
    }

    @Test
    fun `select with invalid estimate should return invalid state`() {
        // act
        viewModel.onSelect(4)

        // assert
        viewModel.viewState.observeForever {
            assert(it is InvalidEstimate)
        }
    }

    @Test
    fun `select with valid estimate should return show estimate back`() {
        // act
        viewModel.onSelect(3)

        // assert
        viewModel.viewState.observeForever {
            assert(it is ShowEstimateBack)
        }
    }

    @Test
    fun `select with first seed should return show estimate back`() {
        // act
        viewModel.onSelect(0)

        // assert
        viewModel.viewState.observeForever {
            assert(it is ShowEstimateBack)
        }
    }

    @Test
    fun `select with second seed should return show estimate back`() {
        // act
        viewModel.onSelect(1)

        // assert
        viewModel.viewState.observeForever {
            assert(it is ShowEstimateBack)
        }
    }
}