package com.ovoskop.marveltest.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
    try {
        val digest: MessageDigest = MessageDigest
            .getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest: ByteArray = digest.digest()

        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun Activity.hideKeyword() {
    val view = currentFocus ?: View(this)

    val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}