package com.example.secondhomework_pages

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)

        val dbHelper = MyDatabaseHelper(this,"Account.db",1)
        val create: Button = findViewById(R.id.create)
        val eaccount: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)

        create.setOnClickListener {
            val ea = eaccount.text.toString()
            val pa = password.text.toString()
            val db = dbHelper.writableDatabase
            val value = ContentValues()
            value.apply {
                put("emailAccount",ea)
                put("password", pa)
            }
            val id = db.insert("Account",null,value)

            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()

            // 跳转回 MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 结束当前页面，防止用户按返回键回到注册页
        }

    }
}