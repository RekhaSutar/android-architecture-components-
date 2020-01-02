package com.rekha.poc.mvvm

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Rekha Sutar on 31-12-2019.
 */
open class BaseFragment : Fragment() ,ActivityFragmentCommunicator{
    override fun showMessage(message: String) {
        baseFunctions.showMessage(message)
    }

    protected lateinit var baseFunctions: ActivityFragmentCommunicator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is ActivityFragmentCommunicator) {
            baseFunctions = activity as ActivityFragmentCommunicator
        }
    }
}

interface ActivityFragmentCommunicator {
    fun showMessage(message: String)
}