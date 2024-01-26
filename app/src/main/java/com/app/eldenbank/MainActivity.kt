package com.app.eldenbank

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * EldenBank app.
 */
class MainActivity : AppCompatActivity() {

    // User Data
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

    /**
     * Called when the activity is first created. Responsible for initializing the UI elements,
     * setting listeners, and configuring the layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGUI()
        setListeners()
    }

    /**
     * Initializes UI elements after the layout has been set.
     */
    private fun initGUI() {
        inputBox = findViewById(R.id.runesEditText)
        checkButton = findViewById(R.id.checkButton)
        depositButton = findViewById(R.id.depositButton)
        withdrawButton = findViewById(R.id.withdrawButton)
        exitButton = findViewById(R.id.exitButton)
        submitButton = findViewById(R.id.submitButton)

        inputBox.visibility = View.INVISIBLE
        submitButton.visibility = View.INVISIBLE
    }

    /**
     * Sets up click listeners for the buttons in the UI.
     */
    private fun setListeners() {
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
        submitButton.setOnClickListener {
            handleSubmit()
        }
        exitButton.setOnClickListener {
            exitApp()
        }
    }

    /**
     * Displays a toast message showing the current rune balance.
     */
    private fun checkRunes() {
        showToast("Checking your runes: Current Balance - $runeBalance")
    }

    /**
     * Adds runes to the balance based on user input.
     */
    private fun depositRunes() {
        if (userInput == 0) {
            showToast("You are loosing everyone's time here.")
        } else if (userInput < 0){
            showToast("You do know we have a withdraw option, right?")
        }
        else {
            runeBalance += userInput
            showToast("Deposited $userInput Runes. Current Balance - $runeBalance")
        }
    }

    /**
     * Withdraws runes from the balance based on user input.
     */
    private fun withdrawRunes() {
        if (runeBalance >= userInput) {
            runeBalance -= userInput
            showToast("Withdrawing $userInput runes. Current Balance - $runeBalance")
        } else {
            showToast("You so poor lol. Get lost.")
        }
    }

    /**
     * Handles the submission of user input, either for deposit or withdrawal.
     */
    private fun handleSubmit() {
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

    /**
     * Toggles the visibility of the input box and submit button.
     */
    private fun displayInput() {
        if (inputBox.visibility == View.INVISIBLE) {
            inputBox.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
            inputBox.requestFocus()
        } else if (inputBox.visibility == View.VISIBLE) {
            inputBox.visibility = View.INVISIBLE
            submitButton.visibility = View.INVISIBLE
        }
    }

    /**
     * Exits the application and displays a toast message with the current rune balance.
     */
    private fun exitApp() {
        finish()
        showToast("Checking Runes: Current Balance - $runeBalance")
    }

    /**
     * Displays a short-duration toast message with the given message.
     *
     * @param message The message to be displayed in the toast.
     */
    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}
