package com.ooommm.seifvoicerec

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ooommm.seifvoicerec.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            Navigation.findNavController(this, R.id.nav_host_fragment_container)
        )
    }

    fun isServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if ("com.ooommm.seifvoicerec.record.RecordService" == service.service.className) {
                Log.d("TAG1", "isServiceRunning: true")
                return true
            }
        }
        Log.d("TAG1", "isServiceRunning: false")
        return false
    }
}