package com.rekha.poc.mvvm


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.rekha.poc.mvvm.model.Employee
import com.rekha.poc.mvvm.viewmodels.EmployeeDetailsViewModel
import kotlinx.android.synthetic.main.fragment_edit.*

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : BaseFragment() {

    private var employeeViewModel: EmployeeDetailsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        employeeViewModel = ViewModelProvider(
            activity!!.viewModelStore,
            ViewModelProvider.NewInstanceFactory()
        ).get(EmployeeDetailsViewModel::class.java)

        et_employee_name.addTextChangedListener(
            EmployeeDetailsTextWatcher(
                context!!,
                til_employee_name,
                R.string.error_employee_name_empty
            )
        )
        et_employee_code.addTextChangedListener(
            EmployeeDetailsTextWatcher( context!!,
                til_employee_code,
                R.string.error_employee_code_empty
            )
        )

        btn_save.setOnClickListener {
            val employeeName = et_employee_name.text.toString()
            val employeeCode = et_employee_code.text.toString()

            var isValidData = true
            if (TextUtils.isEmpty(employeeName.trim())) {
                til_employee_name.error = getString(R.string.error_employee_name_empty)
                isValidData = false
            } else if (TextUtils.isEmpty(employeeCode.trim())) {
                til_employee_code.error = getString(R.string.error_employee_code_empty)
                isValidData = false
            }

            if (isValidData) {
                val employee = Employee(employeeName, employeeCode.toInt())
                if (!employeeViewModel!!.isEmployeeExists(employee)) {
                    employeeViewModel?.addEmployee(employee)
                }else{
                    showMessage(getString(R.string.error_employee_already_exists))
                }
            }

        }
    }

    class EmployeeDetailsTextWatcher(
        private val context: Context,
        private val til: TextInputLayout,
        private val errorId: Int
    ) : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            if (TextUtils.isEmpty(s?.trim())) til.error = context.getString(errorId)
            else til.error = null
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    }


}
