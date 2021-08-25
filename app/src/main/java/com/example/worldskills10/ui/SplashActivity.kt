package com.example.worldskills10.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.worldskills10.R
import com.example.worldskills10.repository.local.DBHelper
import com.example.worldskills10.ui.iniciosesion.InicioSesionActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase
        startTimer()
    }

    private fun startTimer() {
        object :CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(applicationContext, InicioSesionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}