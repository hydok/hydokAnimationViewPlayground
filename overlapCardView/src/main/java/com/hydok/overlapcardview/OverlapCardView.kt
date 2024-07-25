package com.hydok.overlapcardview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

class OverlapCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private var expandDuration = 200L
    private var state = OverlapCardViewSate.OVERLAP
    private val cardViews = ArrayList<View>()
    private var changeListener: ((OverlapCardViewSate) -> Unit?)? = null
    private var overlapTopMargin = 16.toDp(context).toInt()
    private var spreadTopMargin = 108.toDp(context).toInt()

    fun setCard(cards: List<View>) = also {
        cards.forEachIndexed { index, view ->
            view.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                id = View.generateViewId()
                elevation = 3f * index
            }
            this.addView(view)
        }
        cardViews.apply {
            clear()
            addAll(cards)
        }
        setOverlap()
    }

    fun setDuration(duration: Long) {
        expandDuration = duration
    }

    fun setStateChange() = also {
        if (state == OverlapCardViewSate.OVERLAP) {
            setExpaned()
        } else {
            setOverlap()
        }
        changeListener?.invoke(state)
        TransitionManager.beginDelayedTransition(
            this@OverlapCardView.findRootParent(),
            AutoTransition().apply { setDuration(expandDuration) })
    }


    fun setStateListener(listener: (OverlapCardViewSate) -> Unit) = also {
        changeListener = listener
    }

    fun setTopMargin(overlap: Int, spread: Int) = also {
        overlapTopMargin = overlap
        spreadTopMargin = spread
    }

    fun getState() = state

    private fun setExpaned() = also {
        state = OverlapCardViewSate.EXPAND
        ConstraintSet().apply {
            clone(this@OverlapCardView)
            cardViews.forEachIndexed { index, view ->
                val topId = if (index == 0) ConstraintSet.PARENT_ID else cardViews[index - 1].id
                val topMargin = if (index == 0) 0 else spreadTopMargin
                val endSide = if (index == 0) ConstraintSet.TOP else ConstraintSet.BOTTOM
                connect(view.id, ConstraintSet.TOP, topId, endSide, topMargin)
            }
            applyTo(this@OverlapCardView)
        }

    }

    private fun setOverlap() {
        state = OverlapCardViewSate.OVERLAP
        ConstraintSet()
            .run {
                clone(this@OverlapCardView)
                cardViews.forEachIndexed { index, view ->
                    val topId = if (index == 0) ConstraintSet.PARENT_ID else cardViews[index - 1].id
                    val topMargin = if (index == 0) 0 else overlapTopMargin
                    connect(view.id, ConstraintSet.TOP, topId, ConstraintSet.TOP, topMargin)
                }
                applyTo(this@OverlapCardView)
            }
    }


}