package com.goldenthumb.android.chess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..7) {
            if (i % 2 == 0) {
                Log.d(TAG, "i = $i")
            }
        }
    }
}