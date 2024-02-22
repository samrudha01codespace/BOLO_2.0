package com.samrudhasolutions.bolo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class VisualizerView @JvmOverloads constructor(
context: Context,
attrs: AttributeSet? = null,
defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    init {
        setBackgroundColor(Color.WHITE) // Add this line
    }

    private val paint = Paint()
    private var amplitudes: FloatArray? = null

    fun updateVisualizer(amplitudes: FloatArray) {
        this.amplitudes = amplitudes
        invalidate() // Trigger onDraw to redraw the animation
        Log.d("VisualizerView", "updateVisualizer called with amplitudes: ${amplitudes.joinToString()}")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        amplitudes?.let {
            val width = canvas.width.toFloat()
            val height = canvas.height.toFloat()
            val centerY = height / 2

            paint.color = Color.BLUE
            paint.strokeWidth = 5f

            val numPoints = it.size
            val deltaX = width / (numPoints - 1)  // Adjusted to evenly space points

            for (i in 0 until numPoints) {
                val x = i * deltaX
                val y = centerY - it[i] * height / 4  // Adjusted to properly scale and center the visualizer

                canvas.drawPoint(x, y, paint)
            }
        }
    }
}
