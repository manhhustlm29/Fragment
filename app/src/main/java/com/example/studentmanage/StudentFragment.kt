package com.example.studentmanage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentmanage.data.students
import com.example.studentmanage.model.Student

class StudentFragment : Fragment() {
    private val args: StudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.student_fragment, container, false)
        val studentIndex = args.studentIndex
        val nameEditText = view.findViewById<EditText>(R.id.etStudentName)
        val idEditText = view.findViewById<EditText>(R.id.etStudentId)

        if(studentIndex != -1){
            val student = students[studentIndex]
            nameEditText.setText(student.name)
            idEditText.setText(student.id)
        }

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener{
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()

            if(studentIndex == -1) {
                if (name.isNotEmpty() && id.isNotEmpty()) {
                    students.add(Student(name, id))
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                if (name.isNotEmpty() && id.isNotEmpty()) {
                    students[studentIndex] = Student(name, id)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        return view
    }
}