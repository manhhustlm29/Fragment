package com.example.studentmanage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.studentmanage.R
import com.example.studentmanage.model.Student

class StudentAdapter(
    context: Context,
    @LayoutRes private val layoutRes: Int,
    private val students: List<Student>
) : ArrayAdapter<Student>(context, layoutRes, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutRes, parent, false)

        val student = students[position]

        val nameTextView = view.findViewById<TextView>(R.id.tvStudentName)
        val idTextView = view.findViewById<TextView>(R.id.tvStudentId)

        nameTextView.text = student.name
        idTextView.text = student.id

        return view
    }
}