package com.androidchekhov.planningpokerapp

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class PokerActivity : AppCompatActivity(), PokerView {
    lateinit var viewModel: PokerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[PokerViewModel::class.java]

        viewModel.viewState.observe(this, PokerViewStateObserver(this))

        estimateCard.setOnClickListener { viewModel.onCardTap() }
    }

    override fun hideEstimateEntry() {
        estimateEntry.visibility = View.GONE
    }

    override fun showEstimateEntry() {
        estimateEntry.visibility = View.VISIBLE
    }


    override fun hideMessage() {
        messageText.visibility = View.GONE
    }

    override fun hideEstimateCard() {
        estimateCard.visibility = View.GONE
    }

    override fun showEstimateCardBack() = with (estimateCard) {
        visibility = View.VISIBLE
        text = ""
    }

    override fun showEstimateCardFront(estimate: Int) = with (estimateCard) {
        visibility = View.VISIBLE
        text = estimate.toString()
    }

    override fun showInvalidEstimate() = with (messageText) {
        visibility = View.VISIBLE
        text = getString(R.string.invalid_entry_message)
    }

    override fun showTapToReveal() = with(messageText) {
        visibility = View.VISIBLE
        text = getString(R.string.tap_to_reveal_message)
    }

    override fun showResetButton() = with (actionButton) {
        text = getString(R.string.reset)
        setOnClickListener { viewModel.onReset() }
    }

    override fun showSubmitButton() = with(actionButton) {
        text = getString(R.string.submit)
        setOnClickListener {
            val entry = estimateEntry.text.toString()
            when {
                entry.isNotEmpty() -> viewModel.onSelect(entry.toInt())
            }
        }
    }
}
