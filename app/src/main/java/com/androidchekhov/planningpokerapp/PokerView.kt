package com.androidchekhov.planningpokerapp

interface PokerView {
    fun hideMessage()
    fun hideEstimateCard()
    fun hideEstimateEntry()
    fun showEstimateCardBack()
    fun showEstimateCardFront(estimate: Int)
    fun showInvalidEstimate()
    fun showTapToReveal()
    fun showResetButton()
    fun showSubmitButton()
    fun showEstimateEntry()
}