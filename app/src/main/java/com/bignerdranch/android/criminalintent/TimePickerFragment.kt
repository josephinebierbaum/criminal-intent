package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

private const val ARG_TIME = "time"
class TimePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListener = TimePickerDialog.OnTimeSetListener {
                _: TimePicker, hour: Int, minute: Int->
            val date = arguments?.getSerializable(ARG_TIME) as Date
            val y = android.text.format.DateFormat.format("yyyy",date).toString().toInt()
            //Minus one to fix error of adding a month
            val m = android.text.format.DateFormat.format("M",date).toString().toInt()-1
            val d = android.text.format.DateFormat.format("dd",date).toString().toInt()
            val resultDate = GregorianCalendar(y, m, d)
            resultDate.add(Calendar.HOUR, hour)
            resultDate.add(Calendar.MINUTE, minute)
            val resultTime = resultDate.time
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultTime)
            }
        }
        val date = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireContext(),
            timeListener,
            hour,
            minute,
            true
        )
    }
    interface Callbacks {
        fun onTimeSelected(date: Date)
    }
    companion object {
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}