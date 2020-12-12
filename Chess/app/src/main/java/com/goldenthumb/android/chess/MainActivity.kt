package com.goldenthumb.android.chess

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ChessDelegate {

    private var chessModel = ChessModel()
    private lateinit var chessView: ChessView
    private lateinit var printWriter: PrintWriter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            chessModel.reset()
            chessView.invalidate()
        }

        findViewById<Button>(R.id.listen_button).setOnClickListener {
            Log.d(TAG, "socket server listening on port ...")
        }

        findViewById<Button>(R.id.connect_button).setOnClickListener {
            Log.d(TAG, "socket client connecting to addr:port ...")
            Executors.newSingleThreadExecutor().execute {
                val socket = Socket("192.168.0.15", 50000) // use your IP of localhost
                val scanner = Scanner(socket.getInputStream())
                printWriter = PrintWriter(socket.getOutputStream(), true)
                while (scanner.hasNextLine()) {
                    val move = scanner.nextLine().split(",").map { it.toInt() }
                    runOnUiThread {
                        movePiece(move[0], move[1], move[2], move[3])
                    }
                }
            }
        }
    }

    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow)
        chessView.invalidate()
        val moveStr = "$fromCol,$fromRow,$toCol,$toRow"
        Log.d(TAG, moveStr)
        Executors.newSingleThreadExecutor().execute {
            printWriter.println(moveStr)
        }
    }
}