package com.ovoskop.marveltest.presentation.view.`interface`

import java.lang.Exception

interface OnExceptionList {
    fun onError(e: Exception)
    fun onSuccess(size: Int)
    fun onLoad()
}