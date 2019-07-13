package com.androidchekhov.planningpokerapp

sealed class PokerViewState
object Start : PokerViewState()
object InvalidEstimate: PokerViewState()
object ShowEstimateBack: PokerViewState()
data class ShowEstimateFront(val estimate: Int): PokerViewState()