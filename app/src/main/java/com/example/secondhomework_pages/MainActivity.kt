package com.example.secondhomework_pages

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weixinb: Button = findViewById(R.id.wechatLoginBtn)
        val appleb: Button = findViewById(R.id.appleLoginBtn)
        val logind: Button = findViewById(R.id.loginBtn)
        val eaccount: EditText = findViewById(R.id.emailInput)
        val password: EditText = findViewById(R.id.passwordInput)
        val regis: TextView = findViewById(R.id.register)
        val forget: TextView = findViewById(R.id.forgetPassword)

        forget.setOnClickListener {
            Toast.makeText(this,"找回密码功能有待开发", Toast.LENGTH_SHORT).show()
        }

        logind.setOnClickListener {
            val ea = eaccount.text.toString()
            val pa = password.text.toString()

            if(ea.isEmpty() || pa.isEmpty()){
                Toast.makeText(this,"请输入账号和密码", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val dbhelper = MyDatabaseHelper(this,"Account.db",1)
            val db = dbhelper.writableDatabase
            val cursor = db.query("Account",null,"emailAccount = ? and password = ?",arrayOf(ea,pa),null,null,null)
            if(cursor.moveToFirst()){ //有符合项
                cursor.close()
                db.close()
                Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserPage::class.java)
                intent.putExtra("emailAccount",ea)
                intent.putExtra("password",pa)
                startActivity(intent)
            }else{
                cursor.close()
                db.close()
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show()
                eaccount.text.clear()
                password.text.clear()
            }
        }

        regis.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }


        weixinb.setOnClickListener {
            Toast.makeText(this,"Wei xin login", Toast.LENGTH_SHORT).show()
        }
        appleb.setOnClickListener {
            Toast.makeText(this,"Apple login", Toast.LENGTH_SHORT).show()
        }


        // 调整 Apple 按钮图标大小
//        val appleLoginBtn = findViewById<Button>(R.id.appleLoginBtn)
//        val appleIcon = ContextCompat.getDrawable(this, R.drawable.apple)
//        appleIcon?.setBounds(0, 0, dpToPx(24), dpToPx(24))
//        appleLoginBtn.setCompoundDrawables(appleIcon, null, null, null)
//    }
//
//    private fun dpToPx(dp: Int): Int {
//        return (dp * resources.displayMetrics.density).toInt()
    }
}