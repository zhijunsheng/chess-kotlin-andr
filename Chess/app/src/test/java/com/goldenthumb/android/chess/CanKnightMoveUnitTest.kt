package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class CanKnightMoveUnitTest {

    @Test
    fun canKnightMove_singlePiece() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.KNIGHT, -1))
        println(ChessGame)
        assertTrue(ChessGame.canKnightMove(Square(3, 3), Square(5, 4)))
    }
}