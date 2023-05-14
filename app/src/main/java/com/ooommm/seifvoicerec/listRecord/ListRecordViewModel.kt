package com.ooommm.seifvoicerec.listRecord

import androidx.lifecycle.ViewModel
import com.ooommm.seifvoicerec.database.RecordDatabaseDao

class ListRecordViewModel(dataSource: RecordDatabaseDao) : ViewModel() {
    val database = dataSource
    val record = database.getAllRecords()
}