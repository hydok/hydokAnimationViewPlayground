package com.hydok.expandablefloatingbutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.hydok.expandablefloatingbutton.databinding.ViewExpandableFloatingButtonBinding

class ExpandableFloatingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private val binding = ViewExpandableFloatingButtonBinding.inflate(LayoutInflater.from(context), this, true)


    init {

    }

}