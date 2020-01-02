package com.rekha.poc.mvvm

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rekha.poc.mvvm.model.Employee
import com.rekha.poc.mvvm.viewmodels.EmployeeDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var employeeViewModel: EmployeeDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeViewModel = ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)

        employeeViewModel!!.getEmployeeList().observe(this, Observer<List<Employee>> {
            tv_employee_count.setText("" + it.size)
        })
    }

}
