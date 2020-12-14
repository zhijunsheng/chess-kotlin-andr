package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class ChessGameUnitTest {

    @Test
    fun toString_isCorrect() {
        assertTrue(ChessGame.toString().contains("3 . . . . . . . ."))
        assertTrue(ChessGame.toString().contains("0 r n b q k b n r"))
    }

    @Test
    fun movePiece_isCorrect() {
        assertNull(ChessGame.pieceAt(0, 2))
        ChessGame.movePiece(0,1, 0, 2)
        assertNotNull(ChessGame.pieceAt(0, 2))
    }

    @Test
    fun reset_isCorrect() {
        assertNull(ChessGame.pieceAt(0, 2))
        ChessGame.movePiece(0,1, 0, 2)
        assertNotNull(ChessGame.pieceAt(0, 2))
        ChessGame.reset()
        assertNull(ChessGame.pieceAt(0, 2))
    }

    @Test
    fun pieceAt_isCorrect() {
        assertNotNull(ChessGame.pieceAt(0, 0))
        assertEquals(ChessPlayer.WHITE, ChessGame.pieceAt(0,0)?.player)
    }

    @Test

    fun elvisOperator() {
        val x: Int? = null
        val y: Int? = 2
        assertEquals(3, x ?: 3)
        assertEquals(2, y ?: 3)
    }
}