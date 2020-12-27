package com.goldenthumb.android.chess

import kotlin.math.abs

object ChessGame {
    private var piecesBox = mutableSetOf<ChessPiece>()

    init {
        reset()
    }

    fun clear() {
        piecesBox.clear()
    }

    fun addPiece(piece: ChessPiece) {
        piecesBox.add(piece)
    }

    fun canKnightMove(from: Square, to: Square): Boolean {
        return abs(from.col - to.col) == 2 && abs(from.row - to.row) == 1 ||
                abs(from.col - to.col) == 1 && abs(from.row - to.row) == 2
    }

    fun canRookMove(from: Square, to: Square): Boolean {
        if (from.col == to.col ||
            from.row == to.row && isClearHorizontallyBetween(from, to)) {
            return true
        }
        return false
    }

    private fun isClearHorizontallyBetween(from: Square, to: Square): Boolean {
        if (from.row != to.row) return false
        val gap = abs(from.col - to.col) - 1
        if (gap == 0 ) return true
        for (i in 1..gap) {
            val nextCol = if (to.col > from.col) from.col + i else from.col - i
            if (pieceAt(Square(nextCol, from.row)) != null) {
                return false
            }
        }
        return true
    }

    fun canMove(from: Square, to: Square): Boolean {
        if (from.col == to.col && from.row == to.row) {
            return  false
        }
        val movingPiece = pieceAt(from) ?: return false
        when(movingPiece.chessman) {
            Chessman.KNIGHT -> return canKnightMove(from, to)
            Chessman.ROOK -> return canRookMove(from, to)
        }
        return true // FIXME
    }

    fun movePiece(from: Square, to: Square) {
        if (canMove(from, to)) {
            movePiece(from.col, from.row, to.col, to.row)
        }
    }

    private fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        if (fromCol == toCol && fromRow == toRow) return
        val movingPiece = pieceAt(fromCol, fromRow) ?: return

        pieceAt(toCol, toRow)?.let {
            if (it.player == movingPiece.player) {
                return
            }
            piecesBox.remove(it)
        }

        piecesBox.remove(movingPiece)
        addPiece(movingPiece.copy(col = toCol, row = toRow))
    }

    fun reset() {
        clear()
        for (i in 0 until 2) {
            addPiece(ChessPiece(0 + i * 7, 0, Player.WHITE, Chessman.ROOK, R.drawable.rook_white))
            addPiece(ChessPiece(0 + i * 7, 7, Player.BLACK, Chessman.ROOK, R.drawable.rook_black))

            addPiece(ChessPiece(1 + i * 5, 0, Player.WHITE, Chessman.KNIGHT, R.drawable.knight_white))
            addPiece(ChessPiece(1 + i * 5, 7, Player.BLACK, Chessman.KNIGHT, R.drawable.knight_black))

            addPiece(ChessPiece(2 + i * 3, 0, Player.WHITE, Chessman.BISHOP, R.drawable.bishop_white))
            addPiece(ChessPiece(2 + i * 3, 7, Player.BLACK, Chessman.BISHOP, R.drawable.bishop_black))
        }

        for (i in 0 until 8) {
            addPiece(ChessPiece(i, 1, Player.WHITE, Chessman.PAWN, R.drawable.pawn_white))
            addPiece(ChessPiece(i, 6, Player.BLACK, Chessman.PAWN, R.drawable.pawn_black))
        }

        addPiece(ChessPiece(3, 0, Player.WHITE, Chessman.QUEEN, R.drawable.queen_white))
        addPiece(ChessPiece(3, 7, Player.BLACK, Chessman.QUEEN, R.drawable.queen_black))
        addPiece(ChessPiece(4, 0, Player.WHITE, Chessman.KING, R.drawable.king_white))
        addPiece(ChessPiece(4, 7, Player.BLACK, Chessman.KING, R.drawable.king_black))
    }

    fun pieceAt(square: Square): ChessPiece? {
        return pieceAt(square.col, square.row)
    }

    private fun pieceAt(col: Int, row: Int): ChessPiece? {
        for (piece in piecesBox) {
            if (col == piece.col && row == piece.row) {
                return  piece
            }
        }
        return null
    }

    fun pgnBoard(): String {
        var desc = " \n"
        desc += "  a b c d e f g h\n"
        for (row in 7 downTo 0) {
            desc += "${row + 1}"
            desc += boardRow(row)
            desc += " ${row + 1}"
            desc += "\n"
        }
        desc += "  a b c d e f g h"

        return desc
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 7 downTo 0) {
            desc += "$row"
            desc += boardRow(row)
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7"

        return desc
    }

    private fun boardRow(row: Int) : String {
        var desc = ""
        for (col in 0 until 8) {
            desc += " "
            desc += pieceAt(col, row)?.let {
                val white = it.player == Player.WHITE
                when (it.chessman) {
                    Chessman.KING -> if (white) "k" else "K"
                    Chessman.QUEEN -> if (white) "q" else "Q"
                    Chessman.BISHOP -> if (white) "b" else "B"
                    Chessman.ROOK -> if (white) "r" else "R"
                    Chessman.KNIGHT -> if (white) "n" else "N"
                    Chessman.PAWN -> if (white) "p" else "P"
                }
            } ?: "."
        }
        return desc
    }
}