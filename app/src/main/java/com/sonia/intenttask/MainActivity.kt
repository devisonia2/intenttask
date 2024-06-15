package com.sonia.intenttask

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var mail: EditText?=null
    var sub: EditText?=null
    var body: EditText?=null
    var numb: EditText?=null
    var mess: EditText?=null
    var btnmove: Button?=null
    var btnsms: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        mail=findViewById(R.id.mail)
        sub=findViewById(R.id.sub)
        body=findViewById(R.id.body)
        numb=findViewById(R.id.number)
        mess=findViewById(R.id.message)
        btnmove=findViewById(R.id.btnmove)
        btnsms=findViewById(R.id.btnsms)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnmove?.setOnClickListener {
            if(mail?.text?.trim().isNullOrEmpty()){
                mail?.error="Enter The Email"
            }else if(sub?.text?.trim().isNullOrEmpty()){
                sub?.error="Enter The Subject"
            }else if(body?.text?.trim().isNullOrEmpty()){
                body?.error="Enter The Body"
            }
            else {
                val intent=Intent(Intent.ACTION_SENDTO)
                intent.setData(Uri.parse("mailto:"))
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail?.text?.toString()?.trim()))
                intent.putExtra("subject", sub?.text?.toString()?.trim())
                intent.putExtra("body", body?.text?.toString()?.trim())
                startActivity(intent)
            }
        }
        btnsms?.setOnClickListener {
            if (numb?.text?.trim().isNullOrEmpty()){
                numb?.error="Enter The Number"
            } else if((numb?.text?.trim()?.length?:0) <10){
                numb?.error = "Number should be valid"
            } else if(mess?.text?.trim().isNullOrEmpty()){
                mess?.error="Enter The Message"
            }else {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(Uri.parse("smsto:${numb?.text?.toString()?.trim()} "))
                intent.putExtra(Intent.EXTRA_TEXT, mess?.text?.toString()?.trim())
                startActivity(intent)
            }
        }
    }
}