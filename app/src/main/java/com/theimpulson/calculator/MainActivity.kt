package com.theimpulson.calculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialogListener object to show selected date and age results
        val dpdl =
            DatePickerDialog.OnDateSetListener { givenview, selectedyear, selectedmonth, selectedday ->

                val selectedDate = "$selectedday/${selectedmonth + 1}/$selectedyear"
                findViewById<TextView>(R.id.tvSelectedDate).text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selectedDate)

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val selectedDateInMinutes = thedate!!.time / 60000
                val currentDateInMinutes = currentDate!!.time / 60000

                findViewById<TextView>(R.id.ageInMinutes).text =
                    (currentDateInMinutes - selectedDateInMinutes).toString()
            }

        // Create a DatePickerDialog object
        val dpd = DatePickerDialog(this, dpdl, year, month, day)

        // Limit the maximum date allowed to selected till yesterday
        dpd.datePicker.maxDate = Date().time - 86400000

        // Show the DatePickerDialog
        dpd.show()
    }
}