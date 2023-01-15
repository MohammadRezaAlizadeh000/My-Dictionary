package com.mra.mydictionary.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.backgroundBuilder(
    color: Int,
    corner: Int = 0,
    corners: IntArray = intArrayOf(),
    borderColor: Int = 0,
    borderWidth: Int = 0
) = GradientDrawable().apply {
    setColor(ContextCompat.getColor(this@backgroundBuilder, color))

    if (corners.isNotEmpty()) {
        val newCorners = mutableListOf<Float>()
        for (cor in corners.indices) {
            if (corners[cor] != 0){
                newCorners.add(resources.getDimensionPixelSize(corners[cor]).toFloat())
                newCorners.add(resources.getDimensionPixelSize(corners[cor]).toFloat())
            } else{
                newCorners.add(0F)
                newCorners.add(0F)
            }
        }
        cornerRadii = newCorners.toFloatArray()
    } else
        cornerRadius = resources.getDimensionPixelSize(corner).toFloat()

    if (borderWidth and borderColor != 0)
        setStroke(borderWidth, borderColor)

}