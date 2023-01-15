package com.mra.mydictionary.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?) {
    context?.let { _context ->
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()
    }
}