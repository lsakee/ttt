package com.example.myapplication.main

import android.app.Application
import com.example.myapplication.main.util.EncryptedPrefsManger

class TestApplication : Application() {
    companion object {
        lateinit var encryptedPrefs: EncryptedPrefsManger
        lateinit var instance: TestApplication
    }

    override fun onCreate() {
        encryptedPrefs = EncryptedPrefsManger(applicationContext)
        instance = this
        super.onCreate()
    }
}