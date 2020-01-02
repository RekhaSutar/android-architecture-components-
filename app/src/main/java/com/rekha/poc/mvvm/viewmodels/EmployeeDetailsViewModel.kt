package com.rekha.poc.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.rekha.poc.mvvm.model.Employee

/**
 * Created by Rekha Sutar on 30-12-2019.
 */
class EmployeeDetailsViewModel : ViewModel() {

    private var employeeListLiveData = EmployeeListLiveData()

    fun getEmployeeList(): EmployeeListLiveData {
        return employeeListLiveData
    }

    fun addEmployee(employee: Employee) {
        employeeListLiveData.addEmployee(employee)
    }

    fun deleteEmployee(employeeCode: Int) {
        employeeListLiveData.deleteEmployee(employeeCode)
    }

    fun isEmployeeExists(employee: Employee): Boolean {
        return employeeListLiveData.isEmployeeExists(employee)
    }
}