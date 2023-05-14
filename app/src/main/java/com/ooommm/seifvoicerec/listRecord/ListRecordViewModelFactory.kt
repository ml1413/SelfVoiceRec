package com.ooommm.seifvoicerec.listRecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ooommm.seifvoicerec.database.RecordDatabaseDao

class ListRecordViewModelFactory(private val databaseDao: RecordDatabaseDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListRecordViewModel::class.java)) {
            return ListRecordViewModel(databaseDao) as T
        }
        throw
        IllegalAccessException("Unknown ViewModel Class")
    }
}