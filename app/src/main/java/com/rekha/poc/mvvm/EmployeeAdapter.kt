package com.rekha.poc.mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rekha.poc.mvvm.model.Employee
import kotlinx.android.synthetic.main.employee_row.view.*

/**
 * Created by Rekha Sutar on 30-12-2019.
 */
class EmployeeAdapter(private val context: Context,private val listener: EmployeeDeleteListener ) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employeesList: List<Employee>? = null

    fun updateList(employeesList: List<Employee>?) {
        this.employeesList = employeesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.employee_row,
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        if (employeesList != null) return employeesList!!.size
        else return 0
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        employeesList?.get(position)?.let { holder.bind(it) }
    }

    class EmployeeViewHolder(
        val view: View,
        val listener: EmployeeDeleteListener
    ) : RecyclerView.ViewHolder(view) {

        fun bind(employee: Employee) {
            view.tv_employee_name.text = employee.name
            view.tv_employee_code.text = employee.id.toString()
            view.iv_employee_delete.tag = employee.id
            view.iv_employee_delete.setOnClickListener{
                listener.delete(it.tag as Int)
            }
        }
    }

    interface EmployeeDeleteListener{
        fun delete(employeeCode : Int)
    }
}