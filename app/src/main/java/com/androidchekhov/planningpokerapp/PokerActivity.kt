package com.androidchekhov.planningpokerapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class PokerActivity : AppCompatActivity() {

    lateinit var viewModel: PokerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[PokerViewModel::class.java]

        viewModel.viewState.observe(this, Observer { viewState ->
            Log.i(TAG, "updating view state: $viewState")
            viewState?.let {
                when (viewState) {
                    is Start -> handleStart()
                    is InvalidEstimate -> handleInvalidEstimate()
                    is HideEstimate -> handleHideEstimate()
                    is ShowEstimate -> handleShowEstimate(viewState)
                }
            }
        })

        estimateCard.setOnClickListener { viewModel.onCardTap() }
    }

    private fun handleStart() {
        // hide
        messageText.visibility = View.GONE
        estimateCard.visibility = View.GONE

        // show
        estimateEntry.visibility = View.VISIBLE
        actionButton.text = getString(R.string.submit)
        actionButton.setOnClickListener {
            val entry = estimateEntry.text.toString()
            when {
                entry.isNotEmpty() -> viewModel.onSelect(entry.toInt())
            }
        }
    }

    private fun handleInvalidEstimate() {
        // show
        messageText.visibility = View.VISIBLE
        messageText.text = getString(R.string.invalid_entry_message)
    }

    private fun handleHideEstimate() {
        // hide
        estimateEntry.visibility = View.GONE

        // show
        messageText.visibility = View.VISIBLE
        messageText.text = getString(R.string.tap_to_reveal_message)
        estimateCard.visibility = View.VISIBLE
        estimateCard.text = ""
        actionButton.text = getString(R.string.reset)
        actionButton.setOnClickListener { viewModel.onReset() }
    }

    private fun handleShowEstimate(showEstimate: ShowEstimate) {
        // hide
        estimateEntry.visibility = View.GONE
        messageText.visibility = View.GONE

        // show
        estimateCard.text = showEstimate.estimate.toString()
    }

    companion object {
        private const val TAG = "PokerActivity"
    }
}
