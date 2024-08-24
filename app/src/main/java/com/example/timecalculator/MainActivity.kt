package com.example.timecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN:  Button
    private lateinit var buttonDefBTN:  Button

    private lateinit var resultTV: TextView

    private lateinit var resetBTN: Button

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
        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.lastOperandET)
        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDefBTN = findViewById(R.id.buttonDifBTN)
        resultTV = findViewById(R.id.resultTV)
        resetBTN = findViewById(R.id.resetBTN)

        buttonSumBTN.setOnClickListener(this::onClick)
        buttonDefBTN.setOnClickListener(this::onClick)
        resetBTN.setOnClickListener(this::onClick)
    }

    fun onClick(v: View?) {
        val mathematicalOperations = MathematicalOperations()
        val firstOperand = firstOperandET.text.toString()
        val secondOperand = secondOperandET.text.toString()
        var result = 0
        if (mathematicalOperations.transformation(firstOperand) == 0
            || mathematicalOperations.transformation(secondOperand) == 0) {
            resultTV.text = "Неверный формат времени"
        }
        when (v?.id) {
            R.id.buttonSumBTN -> result = mathematicalOperations.addTime(
                mathematicalOperations.transformation(firstOperand),
                mathematicalOperations.transformation(secondOperand)
            )
            R.id.buttonDifBTN -> result = mathematicalOperations.subtractTime(
                mathematicalOperations.transformation(firstOperand),
                mathematicalOperations.transformation(secondOperand)
            )
        R.id.resetBTN -> {
                firstOperandET.text.clear()
                secondOperandET.text.clear()
                resultTV.text = ""
            }
        }
        resultTV.text = mathematicalOperations.formatTime(result)
    }
}