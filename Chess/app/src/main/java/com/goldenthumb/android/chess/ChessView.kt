package com.goldenthumb.android.chess

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private final val originX = 20f
    private final val originY = 200f
    private final val cellSide = 130f
    private final val imgResIDs = setOf(
            R.drawable.bishop_black,
            R.drawable.bishop_white,
            R.drawable.king_black,
            R.drawable.king_white,
            R.drawable.queen_black,
            R.drawable.queen_white,
            R.drawable.rook_black,
            R.drawable.rook_white,
            R.drawable.knight_black,
            R.drawable.knight_white,
            R.drawable.pawn_black,
            R.drawable.pawn_white,
    )
    private final val bitmaps = mutableMapOf<Int, Bitmap>()
    private final val paint = Paint()

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas?) {
        val chessModel = ChessModel()
        chessModel.reset()

        for (row in 0..7) {
            for (col in 0..7) {
                val piece = chessModel.pieceAt(col, row)
                if (piece != null) {
                    drawPieceAt(canvas, col, row, piece.resID)
                }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas?, col: Int, row: Int, resID: Int) {
        val bitmap = bitmaps[resID]!!
        canvas?.drawBitmap(bitmap, null, RectF(originX + col * cellSide,originY + (7 - row) * cellSide,originX + (col + 1) * cellSide,originY + ((7 - row) + 1) * cellSide), paint)
    }

    private fun loadBitmaps() {
        imgResIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessboard(canvas: Canvas?) {
        for (i in 0..7) {
            for (j in 0..7) {
                paint.color = if ((i + j) % 2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas?.drawRect(originX + i * cellSide, originY + j * cellSide, originX + (i + 1)* cellSide, originY + (j + 1) * cellSide, paint)
            }
        }
    }
}
