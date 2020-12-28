package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class RookUnitTest {

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
    fun canRookMove_blockedHorizontally() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.ROOK, -1))
        ChessGame.addPiece(ChessPiece(5, 3, Player.WHITE, Chessman.KNIGHT, -1))

        assertFalse(ChessGame.canMove(Square(3, 3), Square(7, 3)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(6, 3)))
        assertTrue(ChessGame.canMove(Square(3, 3), Square(4, 3)))

        ChessGame.addPiece(ChessPiece(2, 3, Player.WHITE, Chessman.KNIGHT, -1))
        println(ChessGame)
        assertFalse(ChessGame.canMove(Square(3, 3), Square(0, 3)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(1, 3)))
    }

    @Test
    fun canRookMove_blockedVertically() {
        ChessGame.clear()
        ChessGame.addPiece(ChessPiece(3, 3, Player.WHITE, Chessman.ROOK, -1))
        ChessGame.addPiece(ChessPiece(3, 5, Player.WHITE, Chessman.KNIGHT, -1))
        println(ChessGame)

        assertFalse(ChessGame.canMove(Square(3, 3), Square(3, 7)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(3, 6)))
        assertTrue(ChessGame.canMove(Square(3, 3), Square(3, 4)))

        ChessGame.addPiece(ChessPiece(3, 2, Player.WHITE, Chessman.KNIGHT, -1))

        assertFalse(ChessGame.canMove(Square(3, 3), Square(3, 0)))
        assertFalse(ChessGame.canMove(Square(3, 3), Square(3, 1)))
    }
}