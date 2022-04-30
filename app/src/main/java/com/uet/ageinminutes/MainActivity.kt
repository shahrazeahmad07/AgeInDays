package com.uet.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ids
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)

        // btn for date selection.
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCal = Calendar.getInstance()
        val y = myCal.get(Calendar.YEAR)
        val m = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth-${month+1}-$year"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                val formattedSelectedDate = sdf.parse(selectedDate)
                val selectedDateInTime = formattedSelectedDate?.time?.div(86400000)
                val todayDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val todayDateInTime = todayDate?.time?.div(86400000)

                val ageInDays = todayDateInTime?.minus(selectedDateInTime!!)
                tvAgeInDays?.text = ageInDays.toString()
            },
            y,
            m,
            day,
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}