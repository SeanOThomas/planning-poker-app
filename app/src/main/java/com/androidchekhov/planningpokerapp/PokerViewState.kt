package com.androidchekhov.planningpokerapp

sealed class PokerViewState
object Start : PokerViewState()
object InvalidEstimate: PokerViewState()
object HideEstimate: PokerViewState()
data class ShowEstimate(val estimate: Int): PokerViewState()