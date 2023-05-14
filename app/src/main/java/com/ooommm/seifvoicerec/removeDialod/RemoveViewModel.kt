package com.ooommm.seifvoicerec.removeDialod

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.ooommm.seifvoicerec.R
import com.ooommm.seifvoicerec.database.RecordDatabase
import com.ooommm.seifvoicerec.database.RecordDatabaseDao
import kotlinx.coroutines.*
import java.io.File

class RemoveViewModel(
    private var databaseDao: RecordDatabaseDao,
    private val application: Application
) : ViewModel() {
    private var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    fun removeItem(itemId: Long) {
        databaseDao = RecordDatabase.getInstance(application).recordDatabaseDao
        try {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    databaseDao.removeRecord(itemId)
                }
            }
        } catch (e: Exception) {
            Log.e("remove Item", "removeItem: ", e)
        }
    }

    fun removeFile(path: String) {
        val file = File(path)
        if (file.exists()) {
            file.delete()
            Toast.makeText(application, R.string.file_deleted_text, Toast.LENGTH_SHORT).show()
        }
    }
}







