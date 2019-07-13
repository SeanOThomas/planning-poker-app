package com.androidchekhov.planningpokerapp

import android.arch.lifecycle.Observer

class PokerViewStateObserver(
    private val view: PokerView
) : Observer<PokerViewState> {

    override fun onChanged(viewState: PokerViewState?) {
        when (viewState) {
            is Start -> with(view) {
                hideMessage()
                hideEstimateCard()
                showEstimateEntry()
                showSubmitButton()
            }
            is InvalidEstimate -> with(view) {
                showInvalidEstimate()
            }
            is ShowEstimateBack -> with(view) {
                hideEstimateEntry()
                showTapToReveal()
                showEstimateCardBack()
                showResetButton()
            }
            is ShowEstimateFront -> with(view) {
                hideEstimateEntry()
                hideMessage()
                showEstimateCardFront(viewState.estimate)
            }
        }
    }
}