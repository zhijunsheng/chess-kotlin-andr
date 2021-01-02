package com.goldenthumb.android.chess

import org.junit.Test
import org.junit.Assert.*

class PawnUnitTest {

    @Test
    fun canPawnMove_firstMove() {
        println(ChessGame)
        assertTrue(ChessGame.canMove(Square(3, 1), Square(3, 2)))
        assertTrue(ChessGame.canMove(Square(3, 1), Square(3, 3)))
    }
}