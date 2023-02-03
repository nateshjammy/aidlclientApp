package com.example.sampleapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var iDataServiceCallback: IDataServiceCallback? = null

    val connection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            Log.d("MainActivity Client", "Service onBind")

            iDataServiceCallback = IDataServiceCallback.Stub.asInterface(service)

            iDataServiceCallback?.getinstalledAppList()?.forEach {
                Log.d("MainActivity Client", "Service onBind ${it.applicationPackageName}")

            }

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            iDataServiceCallback = null
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent()
        intent.component = ComponentName("com.example.server","com.example.sampleapp.AppService")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }
}