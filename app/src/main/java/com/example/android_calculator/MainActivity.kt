package com.example.android_calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.*


class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener { setTextField("0") }
        btn_1.setOnClickListener { setTextField("1") }
        btn_2.setOnClickListener { setTextField("2") }
        btn_3.setOnClickListener { setTextField("3") }
        btn_4.setOnClickListener { setTextField("4") }
        btn_5.setOnClickListener { setTextField("5") }
        btn_6.setOnClickListener { setTextField("6") }
        btn_7.setOnClickListener { setTextField("7") }
        btn_8.setOnClickListener { setTextField("8") }
        btn_9.setOnClickListener { setTextField("9") }
        btn_minus.setOnClickListener { setTextField("-") }
        btn_plus.setOnClickListener { setTextField("+") }
        btn_multiply.setOnClickListener { setTextField("*") }
        btn_div.setOnClickListener { setTextField("/") }
        btn_leftSk.setOnClickListener { setTextField("(") }
        btn_rightSk.setOnClickListener { setTextField(")") }

        btn_clear.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }

        btn_back.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty()) {
                math_operation.text = str.substring(0, str.length - 1)
            }
            result_text.text = ""
        }

        btn_result.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    result_text.text = longRes.toString()
                } else
                    result_text.text = result.toString()
            } catch(e: Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }
    }

    private fun setTextField(str: String) {
        if (result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }

        math_operation.append(str)
    }
}
