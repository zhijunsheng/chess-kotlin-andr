package com.goldenthumb.android.chess

object ChessGame {
    var piecesBox = mutableSetOf<ChessPiece>()

    init {
        reset()
    }

    fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        if (fromCol == toCol && fromRow == toRow) return
        val movingPiece = pieceAt(fromCol, fromRow) ?: return

        pieceAt(toCol, toRow)?.let {
            if (it.player == movingPiece.player) {
                return
            }
            piecesBox.remove(it)
        }

        piecesBox.remove(movingPiece)
        piecesBox.add(movingPiece.copy(col = toCol, row = toRow))
    }

    fun reset() {
        piecesBox.removeAll(piecesBox)
        for (i in 0 until 2) {
            piecesBox.add(ChessPiece(0 + i * 7, 0, ChessPlayer.WHITE, ChessRank.ROOK, R.drawable.rook_white))
            piecesBox.add(ChessPiece(0 + i * 7, 7, ChessPlayer.BLACK, ChessRank.ROOK, R.drawable.rook_black))

            piecesBox.add(ChessPiece(1 + i * 5, 0, ChessPlayer.WHITE, ChessRank.KNIGHT, R.drawable.knight_white))
            piecesBox.add(ChessPiece(1 + i * 5, 7, ChessPlayer.BLACK, ChessRank.KNIGHT, R.drawable.knight_black))

            piecesBox.add(ChessPiece(2 + i * 3, 0, ChessPlayer.WHITE, ChessRank.BISHOP, R.drawable.bishop_white))
            piecesBox.add(ChessPiece(2 + i * 3, 7, ChessPlayer.BLACK, ChessRank.BISHOP, R.drawable.bishop_black))
        }

        for (i in 0 until 8) {
            piecesBox.add(ChessPiece(i, 1, ChessPlayer.WHITE, ChessRank.PAWN, R.drawable.pawn_white))
            piecesBox.add(ChessPiece(i, 6, ChessPlayer.BLACK, ChessRank.PAWN, R.drawable.pawn_black))
        }

        piecesBox.add(ChessPiece(3, 0, ChessPlayer.WHITE, ChessRank.QUEEN, R.drawable.queen_white))
        piecesBox.add(ChessPiece(3, 7, ChessPlayer.BLACK, ChessRank.QUEEN, R.drawable.queen_black))
        piecesBox.add(ChessPiece(4, 0, ChessPlayer.WHITE, ChessRank.KING, R.drawable.king_white))
        piecesBox.add(ChessPiece(4, 7, ChessPlayer.BLACK, ChessRank.KING, R.drawable.king_black))
    }

    fun pieceAt(col: Int, row: Int) : ChessPiece? {
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
                val white = it.player == ChessPlayer.WHITE
                when (it.rank) {
                    ChessRank.KING -> if (white) "k" else "K"
                    ChessRank.QUEEN -> if (white) "q" else "Q"
                    ChessRank.BISHOP -> if (white) "b" else "B"
                    ChessRank.ROOK -> if (white) "r" else "R"
                    ChessRank.KNIGHT -> if (white) "n" else "N"
                    ChessRank.PAWN -> if (white) "p" else "P"
                }
            } ?: "."
        }
        return desc
    }
}