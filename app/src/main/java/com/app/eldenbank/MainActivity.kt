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
    private var isDeposit: Boolean? = null

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
        showToast("Checking your runes: Current Balance - $runeBalance")
    }

    private fun depositRunes() {
        runeBalance += userInput
        showToast("Deposited $userInput Runes. Current Balance - $runeBalance")
    }

    private fun withdrawRunes() {
        if (runeBalance >= userInput) {
            runeBalance -= userInput
            showToast("Withdrawing $userInput runes. Current Balance - $runeBalance")
        } else {
            showToast("You so poor lol. Get lost.")
        }
    }

    private fun handleSubmit(){
        val rawInput: String = inputBox.toString()
        userInput = rawInput.toInt()

        if (isDeposit!!){
            depositRunes()
        }

        else {
            withdrawRunes()
        }

        displayInput()
    }

    private fun displayInput(){
        if (inputBox.visibility == View.INVISIBLE){
            inputBox.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
            inputBox.requestFocus()
        }
        else if (inputBox.visibility == View.VISIBLE){
            inputBox.visibility = View.INVISIBLE
            submitButton.visibility = View.INVISIBLE
        }
    }

    private fun setListeners(){
        checkButton.setOnClickListener {
            checkRunes()
        }
        depositButton.setOnClickListener {
            isDeposit = true
            displayInput()
        }
        withdrawButton.setOnClickListener {
            isDeposit = false
            displayInput()
        }
        submitButton.setOnClickListener{
            handleSubmit()
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