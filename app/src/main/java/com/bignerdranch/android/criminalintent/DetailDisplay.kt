package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.io.File
import java.util.*
//fragment for showing zoomed in pic

private const val ARG_PIC = "picture"
class DetailDisplay: DialogFragment(){
    private lateinit var picCrime: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_display, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val picture = arguments?.getSerializable(ARG_PIC) as File
        picCrime = view.findViewById(R.id.large_crime_photo) as ImageView
        val bitmap = getScaledBitmap(picture.path, requireActivity())
        picCrime.setImageBitmap(bitmap)
    }
    companion object {
        fun newInstance(picture: File): DialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PIC, picture)
            }
            return DetailDisplay().apply {
                arguments = args
            }
        }
    }
}
