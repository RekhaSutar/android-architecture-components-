package com.rekha.poc.mvvm.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import com.rekha.poc.mvvm.model.Employee

/**
 * Created by Rekha Sutar on 31-12-2019.
 */
class EmployeeListLiveData : LiveData<List<Employee>>() {

    private val list: MutableList<Employee> = mutableListOf()

    fun addEmployee(employee: Employee) {
        list.add(employee)
        value = list
        Log.d("Employee list - ", "" + list.size)
    }

    fun deleteEmployee(employeeCode: Int) {
        list.forEach {
            if (it.id == employeeCode) {
                list.remove(it)
                Log.d("Employee deleted - ", it.name)
                value = list
                return
            }
        }
    }

    fun isEmployeeExists(employee: Employee): Boolean {
        list.forEach {
            if (it.id == employee.id) {
                Log.d("Employee exists - ", it.name)
                return true
            }
        }
        return false
    }

}