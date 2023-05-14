package com.ooommm.seifvoicerec.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ooommm.seifvoicerec.removeDialod.RemoveViewModel

class RemoveViewModelFactory(
    private val databaseDao: RecordDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemoveViewModel::class.java)) {
            return RemoveViewModel(databaseDao, application) as T
        }
        throw IllegalAccessException("UnKnown ViewModel class ")
    }
}