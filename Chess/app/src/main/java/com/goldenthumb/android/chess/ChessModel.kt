package com.goldenthumb.android.chess

class ChessModel {
    var piecesBox = mutableSetOf<ChessPiece>()

    init {
        piecesBox.add(ChessPiece(0,0,ChessPlayer.WHITE, ChessRank.ROOK))
        piecesBox.add(ChessPiece(0,7,ChessPlayer.BLACK, ChessRank.ROOK))
    }

    private fun pieceAt(col: Int, row: Int) : ChessPiece? {
        for (piece in piecesBox) {
            if (col == piece.col && row == piece.row) {
                return  piece
            }
        }
        return null
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 7 downTo 0) {
            desc += "$row"
            for (col in 0..7) {
                val piece = pieceAt(col, row)
                if (piece == null) {
                    desc += " ."
                } else {
                    val white = piece.player == ChessPlayer.WHITE
                    desc += " "
                    desc += when (piece.rank) {
                        ChessRank.KING -> {
                            if (white) "k" else "K"
                        }
                        ChessRank.QUEEN -> {
                            if (white) "q" else "Q"
                        }
                        ChessRank.BISHOP -> {
                            if (white) "k" else "K"
                        }
                        ChessRank.ROOK -> {
                            if (white) "r" else "R"
                        }
                        ChessRank.KNIGHT -> {
                            if (white) "n" else "N"
                        }
                        ChessRank.PAWN -> {
                            if (white) "p" else "P"
                        }
                    }
                }
            }
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7"

        return desc
    }
}