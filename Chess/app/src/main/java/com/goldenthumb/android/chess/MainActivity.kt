package com.goldenthumb.android.chess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val TAG = "MainActivity"

/*
    MVC: Model View Controller
 */
class MainActivity : AppCompatActivity(), ChessDelegate {

    var chessModel = ChessModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "$chessModel")
        val chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this
    }

    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow)
        val chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.invalidate()
    }
}