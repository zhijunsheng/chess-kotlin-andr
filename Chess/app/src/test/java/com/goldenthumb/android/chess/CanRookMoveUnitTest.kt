package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class CanRookMoveUnitTest {

    @Test
    fun canRookMove_singlePiece() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.ROOK, -1))
        println(ChessGame)

        assertTrue(ChessGame.canMove(Square(3, 3), Square(3, 0)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(4, 4)))
        assertTrue(ChessGame.canMove(Square(3, 3), Square(7, 3)))
    }

    @Test
    fun canRookMove_blocked() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.ROOK, -1))
        ChessGame.addPiece(ChessPiece(5, 3, Player.WHITE, Chessman.KNIGHT, -1))
        println(ChessGame)
        assertFalse(ChessGame.canMove(Square(3, 3), Square(7, 3)))
    }
}