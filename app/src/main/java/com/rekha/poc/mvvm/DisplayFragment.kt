package com.rekha.poc.mvvm


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rekha.poc.mvvm.model.Employee
import com.rekha.poc.mvvm.viewmodels.EmployeeDetailsViewModel
import kotlinx.android.synthetic.main.fragment_display.*

/**
 * A simple [Fragment] subclass.
 */
class DisplayFragment : BaseFragment() , EmployeeAdapter.EmployeeDeleteListener {

    private var employeeViewModel: EmployeeDetailsViewModel? = null
    private var adapter : EmployeeAdapter? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        employeeViewModel = ViewModelProvider(activity!!.viewModelStore, ViewModelProvider.NewInstanceFactory()).get(EmployeeDetailsViewModel::class.java)

        adapter = EmployeeAdapter(activity!!.applicationContext, this)
        rv_employee_list.adapter = adapter

        employeeViewModel!!.getEmployeeList().observe(activity as LifecycleOwner,
            Observer<List<Employee>> { it ->
                adapter?.updateList(it)
                adapter?.notifyDataSetChanged()
            })

    }

    override fun delete(employeeCode: Int) {
        employeeViewModel?.deleteEmployee(employeeCode)
    }

}
