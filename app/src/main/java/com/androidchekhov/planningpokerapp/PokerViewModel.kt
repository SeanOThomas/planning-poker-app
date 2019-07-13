package com.androidchekhov.planningpokerapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class PokerViewModel: ViewModel() {

    var viewState: MutableLiveData<PokerViewState> = MutableLiveData()

    private var estimate: Int? = null

    init {
        viewState.value = Start
    }

    fun onReset() {
        viewState.value = Start
    }

    fun onCardTap() {
        viewState.value = ShowEstimateFront(checkNotNull(estimate))
    }

    fun onSelect(estimate: Int) {
        viewState.value = when {
            isValidEstimate(estimate) -> {
                this.estimate = estimate
                ShowEstimateBack
            }
            else -> InvalidEstimate
        }
    }

    /**
     * Checks if [estimate] occurs within Fibonacci series.
     * @see [NUM_ESTIMATES] [SEED_ESTIMATE_FIRST] [SEED_ESTIMATE_SECOND]
     */
    private fun isValidEstimate(estimate: Int): Boolean {
        var first = SEED_ESTIMATE_FIRST
        var second = SEED_ESTIMATE_SECOND

        for (i in 1..NUM_ESTIMATES) {
            when (estimate) {
                first, second -> return true
                else -> {
                    val sum = first + second
                    first = second
                    second = sum
                }
            }
        }
        return false
    }

    companion object {
        private const val NUM_ESTIMATES = 20
        private const val SEED_ESTIMATE_FIRST = 0
        private const val SEED_ESTIMATE_SECOND = 1
    }
}