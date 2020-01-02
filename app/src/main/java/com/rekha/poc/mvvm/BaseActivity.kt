package com.rekha.poc.mvvm

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Rekha Sutar on 31-12-2019.
 */
open class BaseActivity : AppCompatActivity() , ActivityFragmentCommunicator{
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}