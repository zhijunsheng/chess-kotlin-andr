package com.goldenthumb.android.chess

import junit.framework.TestCase

class ChessGameTest : TestCase() {

    fun testToString() {
        assertTrue(ChessGame.toString().contains("0 r n b q k b n r"))
    }

    fun testMovePiece() {
        assertNull(ChessGame.pieceAt(0, 2))
        ChessGame.movePiece(0,1, 0, 2)
        assertNotNull(ChessGame.pieceAt(0, 2))
    }

    fun testReset() {
        assertNull(ChessGame.pieceAt(0, 2))
        ChessGame.movePiece(0,1, 0, 2)
        assertNotNull(ChessGame.pieceAt(0, 2))
        ChessGame.reset()
        assertNull(ChessGame.pieceAt(0, 2))
    }

    fun testPieceAt() {
        assertNotNull(ChessGame.pieceAt(0, 0))
        assertEquals(ChessPlayer.WHITE, ChessGame.pieceAt(0,0)?.player)
    }

    fun testAdd() {
        assertEquals(13, add(3, 10))
    }

    private fun add(a: Int, b: Int) : Int {
        return  a + b
    }
}