package com.example.timecalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar

    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN:  Button
    private lateinit var buttonDefBTN:  Button

    private lateinit var resultTV: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = getString(R.string.timeCalculator)
        toolbarMain.subtitle = getString(R.string.version)
        toolbarMain.setLogo(R.drawable.ic_calculator)

        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.lastOperandET)
        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDefBTN = findViewById(R.id.buttonDifBTN)
        resultTV = findViewById(R.id.resultTV)

        buttonSumBTN.setOnClickListener(this::onClick)
        buttonDefBTN.setOnClickListener(this::onClick)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstOperandET.text.clear()
                secondOperandET.text.clear()
                resultTV.text = getString(R.string.result)
                resultTV.setTextColor(resources.getColor(R.color.black))
                Toast.makeText(
                    applicationContext,
                    getString(R.string.resetToast),
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.exitMenuMain -> {
                finish()
                Toast.makeText(
                    applicationContext,
                    getString(R.string.app_closed),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onClick(v: View?) {
        val mathematicalOperations = MathematicalOperations()
        val firstOperand = firstOperandET.text.toString()
        val secondOperand = secondOperandET.text.toString()
        var result = 0
        if (mathematicalOperations.transformation(firstOperand) == 0
            || mathematicalOperations.transformation(secondOperand) == 0) {
            resultTV.text = R.string.Incorrect_time_format.toString()
        }
        when (v?.id) {
            R.id.buttonSumBTN -> {
                result = mathematicalOperations.addTime(
                    mathematicalOperations.transformation(firstOperand),
                    mathematicalOperations.transformation(secondOperand)
                )
                resultTV.setTextColor(resources.getColor(R.color.red))
            }
            R.id.buttonDifBTN -> {
                result = mathematicalOperations.subtractTime(
                    mathematicalOperations.transformation(firstOperand),
                    mathematicalOperations.transformation(secondOperand)
                )
                resultTV.setTextColor(resources.getColor(R.color.red))
            }

        }
        resultTV.text = mathematicalOperations.formatTime(result)
        Toast.makeText(
            applicationContext,
            "${getString(R.string.resetToast)}: ${mathematicalOperations.formatTime(result)}",
            Toast.LENGTH_LONG
        ).show()
    }
}