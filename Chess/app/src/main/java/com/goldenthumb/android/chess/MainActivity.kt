package com.goldenthumb.android.chess

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ChessDelegate {
    private val socketHost = "127.0.0.1"
    private val socketPort: Int = 50000
    private val socketGuestPort: Int = 50001 // used for socket server on emulator
    private lateinit var chessView: ChessView
    private var printWriter: PrintWriter? = null
    private val isEmulator = Build.FINGERPRINT.contains("generic")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            ChessGame.reset()
            chessView.invalidate()
        }

        findViewById<Button>(R.id.listen_button).setOnClickListener {
            val port = if (isEmulator) socketGuestPort else socketPort
            Log.d(TAG, "socket server listening on $port")
            Executors.newSingleThreadExecutor().execute {
                val serverSocket = ServerSocket(port)
                val socket = serverSocket.accept()
                receiveMove(socket)
            }
        }

        findViewById<Button>(R.id.connect_button).setOnClickListener {
            Log.d(TAG, "socket client connecting ...")
            Executors.newSingleThreadExecutor().execute {
                val socket = Socket(socketHost, socketPort)
                receiveMove(socket)
            }
        }
    }

    private fun receiveMove(socket: Socket) {
        val scanner = Scanner(socket.getInputStream())
        printWriter = PrintWriter(socket.getOutputStream(), true)
        while (scanner.hasNextLine()) {
            val move = scanner.nextLine().split(",").map { it.toInt() }
            runOnUiThread {
                ChessGame.movePiece(move[0], move[1], move[2], move[3])
                chessView.invalidate()
            }
        }
    }

    override fun pieceAt(col: Int, row: Int): ChessPiece? = ChessGame.pieceAt(col, row)

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        Log.d(TAG, "$fromCol,$fromRow,$toCol,$toRow")
        ChessGame.movePiece(fromCol, fromRow, toCol, toRow)
        chessView.invalidate()

        printWriter?.let {
            val moveStr = "$fromCol,$fromRow,$toCol,$toRow"
            Executors.newSingleThreadExecutor().execute {
                it.println(moveStr)
            }
        }
    }
}