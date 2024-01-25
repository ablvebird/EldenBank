package com.app.eldenbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var runeBalance: Int = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()

    }

    private fun checkRunes() {
        showToast("Checking Runes: Current Balance - $runeBalance")
    }

    private fun depositRunes() {
        runeBalance += 10
        showToast("Deposited 10 Runes. Current Balance - $runeBalance")
    }

    private fun withdrawRunes() {
        if (runeBalance >= 10) {
            runeBalance -= 10
            showToast("Withdrawn 10 Runes. Current Balance - $runeBalance")
        } else {
            showToast("Insufficient Runes for withdrawal.")
        }
    }

    private fun setListeners(){
        // Initialize UI components
        val checkButton: Button = findViewById(R.id.checkButton)
        val depositButton: Button = findViewById(R.id.depositButton)
        val withdrawButton: Button = findViewById(R.id.withdrawButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        checkButton.setOnClickListener {
            checkRunes()
        }
        depositButton.setOnClickListener {
            depositRunes()
        }
        withdrawButton.setOnClickListener {
            withdrawRunes()
        }
        exitButton.setOnClickListener {
            exitApp()
        }
    }

    private fun exitApp(){
        finish()
        showToast("Checking Runes: Current Balance - $runeBalance")
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}