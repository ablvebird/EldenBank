package com.app.eldenbank
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    //User Data
    private var runeBalance: Int = 10000
    private var userInput: Int = 0

    //GUI Elements
    private val inputBox:EditText = findViewById(R.id.runesEditText)
    private val checkButton: Button = findViewById(R.id.checkButton)
    private val depositButton: Button = findViewById(R.id.depositButton)
    private val withdrawButton: Button = findViewById(R.id.withdrawButton)
    private val exitButton: Button = findViewById(R.id.exitButton)
    private val submitButton: Button = findViewById(R.id.submitButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun checkRunes() {
        showToast("Checking Runes: Current Balance - $runeBalance")
    }

    private fun depositRunes() {
        /*  1) Make inputBox and submit button visible
            2) Receive the user input and stores in variable
            3) Adds the amount to the current runes
         */
        openInput()

        runeBalance += userInput
        showToast("Deposited $userInput Runes. Current Balance - $runeBalance")
    }

    private fun withdrawRunes() {
        if (runeBalance >= 10) {
            runeBalance -= 10
            showToast("Withdrawn $userInput Runes. Current Balance - $runeBalance")
        } else {
            showToast("Insufficient Runes for withdrawal.")
        }
    }

    private fun openInput(){
        inputBox.visibility = View.VISIBLE
        submitButton.visibility = View.VISIBLE
        inputBox.requestFocus()
    }

    private fun handleInput(){

    }

    private fun setListeners(){
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
        submitButton.setOnClickListener{

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