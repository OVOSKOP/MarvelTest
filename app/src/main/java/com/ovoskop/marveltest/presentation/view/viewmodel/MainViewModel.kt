package com.ovoskop.marveltest.presentation.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovoskop.marveltest.presentation.interactor.SearchInteractor
import com.ovoskop.marveltest.presentation.view.`interface`.OnExceptionList
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel() : ViewModel() {

    var error: MutableLiveData<Boolean> = MutableLiveData(false)
    var load: MutableLiveData<Boolean> = MutableLiveData(false)
    var empty: MutableLiveData<Boolean> = MutableLiveData(false)

    val onError: OnExceptionList = object : OnExceptionList {
        override fun onError(e: Exception) {
            error.value = true
            load.value = false
            empty.value = false
        }
        override fun onSuccess(size: Int) {
            if (size == 0) empty.value = true
            load.value = false
        }
        override fun onLoad() {
            load.value = true
            error.value = false
            empty.value = false
        }
    }

}