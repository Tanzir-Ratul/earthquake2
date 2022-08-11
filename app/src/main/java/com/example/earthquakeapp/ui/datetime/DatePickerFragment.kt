package com.example.earthquakeapp.ui.datetime

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment(val callback: (String) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireContext(), this, year, month, day)
    }
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
       val c = Calendar.getInstance()
        c.set(p1,p2,p3)
        val selectDate = SimpleDateFormat("yyyy-MM-dd").format(Date(c.timeInMillis))
        callback(selectDate)
    }


}