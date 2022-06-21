package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private var txtSelectedDate : TextView? = null
    private var txtTimeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        txtTimeInMinutes = findViewById(R.id.txtTimeInMinutes)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }


    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = //        DatePickerDialog.OnDateSetListener
            DatePickerDialog(
                this,
                {_, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    txtSelectedDate?.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)


                    val theDate = sdf.parse(selectedDate)

                    theDate?.let {
                        val selectedDateInMinutes = theDate.time / 60000
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                        currentDate?.let {
                            val currentDateInMinutes = currentDate.time / 60000
                            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                            txtTimeInMinutes?.text = differenceInMinutes.toString()
                        }

                    }






                },
                year,
                month,
                day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400
        dpd.show()


    }

}