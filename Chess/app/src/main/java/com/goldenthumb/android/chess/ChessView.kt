package com.goldenthumb.android.chess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private final val originX = 20f
    private final val originY = 200f
    private final val cellSide = 130f

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        for (i in 0..7) {
            for (j in 0..7) {
                paint.color = if ((i + j) % 2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas?.drawRect(originX + i * cellSide, originY + j * cellSide, originX + (i + 1)* cellSide, originY + (j + 1) * cellSide, paint)
            }
        }
    }
}
