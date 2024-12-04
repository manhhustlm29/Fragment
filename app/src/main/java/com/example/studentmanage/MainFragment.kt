package com.example.studentmanage

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.studentmanage.adapter.StudentAdapter
import com.example.studentmanage.data.students
import com.example.studentmanage.model.Student

class MainFragment : Fragment(),MenuProvider {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment,container,false)
        listView = view.findViewById(R.id.lvStudents)

        adapter = StudentAdapter(requireContext(),R.layout.item_student,students)
        listView.adapter = adapter
        registerForContextMenu(listView)

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        return view
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.add_new -> {
                findNavController().navigate(R.id.action_mainFragment_to_studentFragment)
                true
            }
            else -> false
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when(item.itemId){
            R.id.edit->{
                val action = MainFragmentDirections.actionMainFragmentToStudentFragment(info.position)
                findNavController().navigate(action)
            }
            R.id.remove->{
                adapter.remove(adapter.getItem(info.position))
            }
        }
        return super.onContextItemSelected(item)
    }
}