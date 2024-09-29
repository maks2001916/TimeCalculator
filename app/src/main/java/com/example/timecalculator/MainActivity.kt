package com.example.timecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        title = R.string.timeCalculator.toString()
        toolbarMain.subtitle = R.string.version.toString()
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
                resultTV.text = R.string.result.toString()
            }
            R.id.exitMenuMain -> finish()
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

        }
        resultTV.text = mathematicalOperations.formatTime(result)
    }
}