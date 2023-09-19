package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var reset = false
    var number1 = 0.0
    var number2 = 0.0
    var isEqulasPress = false
    var pressAddinationBtn = false
    var pressMinusBtn = false
    var pressMultiplyBtn = false
    var pressDivideBtn = false
    var isBtnClearPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        listeners()
        setContentView(binding.root)
    }

    fun listeners() {
        binding.btnZero.setOnClickListener {
            if (binding.textView.text.toString() != "0") {
                numberEntry("0")
            }
        }

        binding.textView.setMovementMethod(ScrollingMovementMethod());

        binding.btnOne.setOnClickListener {
            numberEntry("1")
        }

        binding.btnTwo.setOnClickListener {
            numberEntry("2")
        }

        binding.btnThree.setOnClickListener {
            numberEntry("3")
        }

        binding.btnFour.setOnClickListener {
            numberEntry("4")
        }

        binding.btnFive.setOnClickListener {
            numberEntry("5")
        }

        binding.btnSix.setOnClickListener {
            numberEntry("6")
        }

        binding.btnSeven.setOnClickListener {
            numberEntry("7")
        }

        binding.btnEight.setOnClickListener {
            numberEntry("8")
        }

        binding.btnNine.setOnClickListener {
            numberEntry("9")
        }

        binding.btnDot.setOnClickListener {
            addDot()
        }

        binding.btnClear.setOnClickListener {
            clear()
        }

        binding.btnDelete.setOnClickListener {
            delete()
        }

        binding.btnAddition.setOnClickListener {
            pressAddinationBtn = true
            setNumber1()
        }

        binding.btnDivide.setOnClickListener {
            pressDivideBtn = true
            setNumber1()
        }

        binding.btnMinus.setOnClickListener {
            pressMinusBtn = true
            setNumber1()
        }

        binding.btnMultiply.setOnClickListener {
            pressMultiplyBtn = true
            setNumber1()
        }

        binding.btnNegative.setOnClickListener {
            addMinusSign()
        }

        binding.btnPercent.setOnClickListener {
            getPercentage()
        }

        binding.btnEqual.setOnClickListener {
            equal()
        }
    }

    fun addDot() {
        if (!binding.textView.text.toString().contains('.')) {
            binding.textView.text = binding.textView.text.toString() + "."
        }
    }

    fun convertToInt(number: Double): String {
        if (number % 1 == 0.0) {
            val str = number.toInt().toString()
            return str.replace(",", ".")
        } else {
            return String.format("%.2f", number).replace(",", ".")
        }
    }

    fun equal() {
        var result = ""
        isEqulasPress = true
        number2 = binding.textView.text.toString().toDouble()

        val textNumber1 = convertToInt(number1)
        val textNumber2 = convertToInt(number2)

        if (pressAddinationBtn) {
            binding.textPrevious.text = "$textNumber1+$textNumber2"
            result = convertToInt(number1 + number2)
            binding.textView.text = result
            pressAddinationBtn = false
        }
        if (pressMinusBtn) {
            binding.textPrevious.text = "$textNumber1-$textNumber2"
            result = convertToInt(number1 - number2)
            binding.textView.text = result
            pressMinusBtn = false
        }
        if (pressMultiplyBtn) {
            binding.textPrevious.text = "$textNumber1 x $textNumber2"
            result = convertToInt(number1 * number2)
            binding.textView.text = result
            pressMultiplyBtn = false
        }
        if (pressDivideBtn) {
            binding.textPrevious.text = "$textNumber1/$textNumber2"
            result = convertToInt(number1 / number2)
            binding.textView.text = result
            pressDivideBtn = false
        }
    }

    fun setNumber1() {
        Log.e("hata", binding.textView.text.toString())
        number1 = binding.textView.text.toString().toDouble()

        val textNumber = convertToInt(number1)

        if (pressAddinationBtn) {
            binding.textPrevious.text = "$textNumber+"
        }
        if (pressMinusBtn) {
            binding.textPrevious.text = "$textNumber-"
        }
        if (pressMultiplyBtn) {
            binding.textPrevious.text = "${textNumber} x"
        }
        if (pressDivideBtn) {
            binding.textPrevious.text = "${textNumber} /"
        }
        clearAfterOperationBtnPress()
    }

    fun getPercentage() {
        var value = binding.textView.text.toString().toDouble()
        if (value != 0.0) {
            value /= 100.0
            binding.textView.text = value.toString()
            reset = true
        }
    }


    fun numberEntry(value: String) {

        if (isEqulasPress) {
            binding.textView.text = ""
            binding.textPrevious.text = ""
            isEqulasPress = false
        }

        if (binding.textView.text.toString().length < 4) {
            if (binding.textView.text.toString() == "0") {
                binding.textView.text = ""
            }
            binding.textView.text = binding.textView.text.toString() + value
            if (reset == true) {
                reset = false
                binding.textView.text = value
            }
        }
    }

    fun addMinusSign() {
        if (binding.textView.text.isNotEmpty() && binding.textView.text[0] != '-') {
            binding.textView.text = "-${binding.textView.text.toString()}"
        } else if (binding.textView.text.isNotEmpty() && binding.textView.text[0] == '-') {
            binding.textView.text = binding.textView.text.toString().substring(1)
        }
    }

    fun clearAfterOperationBtnPress() {
        binding.textView.text = "0"
    }

    fun clear() {


        binding.textPrevious.text = ""
        binding.textView.text = "0"
        isBtnClearPressed = false

        pressAddinationBtn = false
        pressMinusBtn = false
        pressMultiplyBtn = false
        pressDivideBtn = false


    }

    fun delete() {
        if (binding.textView.text.isNotEmpty()) {
            if (binding.textView.text.length == 1) {
                binding.textView.text="0"
            } else {
                binding.textView.text =
                    binding.textView.text.toString().substring(0, binding.textView.text.length - 1)
            }
        }
    }


}