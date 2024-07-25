package com.hydok.overlapcardview

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

fun Int.toDp(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics
)

fun View.findRootParent(): ViewGroup {
    var rootView: View = this
    while (rootView.parent is ViewGroup) {
        rootView = rootView.parent as ViewGroup
    }
    return rootView as ViewGroup
}