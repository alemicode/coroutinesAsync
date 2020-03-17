package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val RESULT_1 = "RESULT1"
    val RESULT_2 = "RESULT2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click_btn.setOnClickListener {

            // setNewText("Clicked")
            fakeApiRequest()


        }
    }

    private fun fakeApiRequest() {

        var startTime = System.currentTimeMillis()

        CoroutineScope(IO).launch {

            var time_finished = measureTimeMillis {
                var resutl = async {

                    for (i in 1..1000000000)
                        if(i == 150)
                            "result 1"
                        else
                            "0"

                }.await()

                var resut2 = async {

                    for (i in 1000000000..2000000000)
                        if(i == 150)
                            "result 2"
                        else
                            "0"

                }.await()
            }


            //first result 1 start
            //after that result two

            println("task finished at ${time_finished}")

        }
    }

    private fun setNewText(input: String) {
        var newText = tv_pallaerJob.text.toString() + "\n$input"
        tv_pallaerJob.text = newText
    }

    private suspend fun setTextInMainThread(input: String) {
        withContext(Main) {
            setNewText(input)
        }
    }

    private suspend fun getRersultFromApiOne(): String {

         delay(1000)
        return RESULT_1
    }

    private suspend fun getResultFromApiTwo(): String {
        delay(1700)
        return RESULT_2
    }
}
