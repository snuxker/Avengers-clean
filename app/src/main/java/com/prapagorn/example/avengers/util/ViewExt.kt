package com.prapagorn.example.avengers.util

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}