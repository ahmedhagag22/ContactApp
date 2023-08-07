package com.example.contactappassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetilsActivty : AppCompatActivity() {
    lateinit var userName:TextView
    lateinit var phone:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detils_activty)

        userName=findViewById(R.id.tv_ProfileName);
        phone=findViewById(R.id.tv_profilePhone);
        var strUser :String?=intent.getStringExtra("name")
        var strphone :String?=intent.getStringExtra("phone")

        userName.setText(strUser)
        phone.setText(strphone)



    }
}