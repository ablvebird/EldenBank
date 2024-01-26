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

    // GUI Elements
    private lateinit var inputBox: EditText
    private lateinit var checkButton: Button
    private lateinit var depositButton: Button
    private lateinit var withdrawButton: Button
    private lateinit var exitButton: Button
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGUI()
        setListeners()
    }

    // Initialize UI elements after setContentView
    private fun initGUI(){
        inputBox = findViewById(R.id.runesEditText)
        checkButton = findViewById(R.id.checkButton)
        depositButton = findViewById(R.id.depositButton)
        withdrawButton = findViewById(R.id.withdrawButton)
        exitButton = findViewById(R.id.exitButton)
        submitButton = findViewById(R.id.submitButton)
    }

    //Checks balance
    private fun checkRunes() {
        showToast("Checking your runes: Current Balance - $runeBalance")
    }

    //Adds runes to balance
    private fun depositRunes() {
        runeBalance += userInput
        showToast("Deposited $userInput Runes. Current Balance - $runeBalance")
    }

    //Withdraw runes
    private fun withdrawRunes() {
        if (runeBalance >= userInput) {
            runeBalance -= userInput
            showToast("Withdrawing $userInput runes. Current Balance - $runeBalance")
        } else {
            showToast("You so poor lol. Get lost.")
        }
    }

    //Method for submitting the user input
    private fun handleSubmit(){
        val rawInput: String = inputBox.text.toString()

        // Check if input is not empty
        if (rawInput.isNotEmpty()) {
            userInput = rawInput.toIntOrNull() ?: 0 // Use 0 as a default if conversion fails

            if (isDeposit == true) {
                depositRunes()
            } else {
                withdrawRunes()
            }

            displayInput()
        } else {
            showToast("Please enter a valid number.")
        }
    }

    //Toggle for input box display
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

    //Button listeners
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

    //Exit method
    private fun exitApp(){
        finish()
        showToast("Checking Runes: Current Balance - $runeBalance")
    }

    //Notifications
    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}