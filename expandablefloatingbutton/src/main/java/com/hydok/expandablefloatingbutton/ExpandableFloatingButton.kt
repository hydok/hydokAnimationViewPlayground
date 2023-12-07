package com.hydok.expandablefloatingbutton

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.hydok.expandablefloatingbutton.databinding.ViewExpandableFloatingButtonBinding

class ExpandableFloatingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private var isExpanded = true //state
    private var baseWidth = 0 // 펼쳐짐 width
    private var baseHeight = 0
    private var startEndPaddingValueDp = 15 //left right padding 값
    private var topBottomPaddingValueDp = 8 //top bottom padding 값
    private var expandDuration: Long = 300 //펼침 닫힘 애니메이션 시간
    private var iconAnimator: ObjectAnimator? = null //아이콘 움직임 애니메이터

    private val binding =
        ViewExpandableFloatingButtonBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        setStartEndPadding(startEndPaddingValueDp)

        this.post {
            baseWidth = width
            baseHeight = height
        }
    }


    fun setTitle(title: String, @ColorRes color: Int = 0, size: Float = 14f) = also {
        binding.title.apply {
            text = title
            textSize = size
            if (color != 0)
                setTextColor(context.resources.getColor(color, null))
        }
    }

    fun setIcon(@DrawableRes image: Int, widthDp :Int = 32) = also {
        binding.icon.setImageResource(image)
        binding.icon.setWidth(widthDp.toPx())
    }

    fun setColor(@ColorRes color: Int) = also {
        binding.floatingView.setCardBackgroundColor(context.resources.getColor(color, null))
    }

    fun setExpanded() {
        if (!isExpanded) {
            isExpanded = true
            binding.title.fadeIn(expandDuration)
            startWidthAnimation(baseWidth)
        }
    }


    fun setCollapsed() {
        if (isExpanded) {
            isExpanded = false
            binding.title.isVisible = false
            startWidthAnimation(baseHeight)
            setStartEndPadding(0)
        }
    }

    fun setExpandDuration(duration: Long) = also {
        expandDuration = duration
    }

    private fun setStartEndPadding(dp: Int) {
        val startEndPadding = dp.toPx()
        val topBottomPadding = topBottomPaddingValueDp.toPx()
        binding.innerLayout.setPadding(startEndPadding, topBottomPadding, startEndPadding, topBottomPadding)
    }

    private fun startWidthAnimation(widthPx: Int) {
        val view = binding.floatingView

        val animator = ValueAnimator.ofInt(view.width, widthPx)
        animator.interpolator = OvershootInterpolator()
        animator.duration = expandDuration

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int

            view.layoutParams.width = animatedValue
            view.requestLayout()
        }
        animator.start()
    }

    private fun startIconAnimation() {
        if (iconAnimator == null) {
            val view = binding.icon
            iconAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, 10f)
                .apply {
                    interpolator = AnticipateInterpolator()
                    duration = 1000
                    repeatMode = ValueAnimator.REVERSE
                    repeatCount = ValueAnimator.INFINITE
                }
        }
        iconAnimator?.start()
    }

    private fun stopIconAnimation() = iconAnimator?.cancel()

    private fun View.fadeIn(ms: Long) {
        if (this.visibility == View.GONE) {
            visibility = View.VISIBLE
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(ms)
                .setListener(null)
        }
    }

    private fun Int.toPx(): Int = (this * context.resources.displayMetrics.density).toInt()

    private fun View.setWidth(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.width = value
            layoutParams = lp
        }
    }
}