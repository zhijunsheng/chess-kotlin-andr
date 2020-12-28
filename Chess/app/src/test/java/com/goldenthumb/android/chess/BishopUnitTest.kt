package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class BishopUnitTest {

    @Test
    fun canBishopMove_singlePiece() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.BISHOP, -1))
        println(ChessGame)
        assertTrue(ChessGame.canMove(Square(3, 3), Square(7, 7)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(5, 4)))
    }
}