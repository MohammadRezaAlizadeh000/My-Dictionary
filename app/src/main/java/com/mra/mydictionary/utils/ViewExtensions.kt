package com.mra.mydictionary.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?) {
    context?.let { _context ->
        Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toast(@StringRes message: Int) {
    context?.let { _context ->
        Toast.makeText(_context, _context.getString(message), Toast.LENGTH_SHORT).show()
    }
}