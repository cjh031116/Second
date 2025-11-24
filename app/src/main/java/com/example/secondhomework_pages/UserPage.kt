package com.example.secondhomework_pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_page)

        val emailAccount = intent.getStringExtra("emailAccount")
        val password = intent.getStringExtra("password")
        val account: TextView = findViewById(R.id.accountName)
        val personSign: TextView = findViewById(R.id.personSign)

        //使用sharePreferences保存个性签名并设置默认值
        val editor = getSharedPreferences("personalSign",Context.MODE_PRIVATE).edit()
        editor.putString("sign","她掉一滴泪，我屠一座城~")
        editor.apply()

        val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
        personSign.text = prefs.getString("sign","欢迎来到信息App~")

        account.text = emailAccount
        // 设置 RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_id)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //多设置几个item才能显示出滚动窗口的优势
        val menuItems = listOf(
            MenuItem(R.drawable.person, "个人信息"),
            MenuItem(R.drawable.aboutus, "关于我们"),
            MenuItem(R.drawable.advice, "意见反馈"),
            MenuItem(R.drawable.preferences, "我的收藏"),
            MenuItem(R.drawable.history, "浏览历史"),
            MenuItem(R.drawable.community, "社区"),
            MenuItem(R.drawable.person, "个人信息"),
            MenuItem(R.drawable.aboutus, "关于我们"),
            MenuItem(R.drawable.advice, "意见反馈"),
            MenuItem(R.drawable.preferences, "我的收藏"),
            MenuItem(R.drawable.history, "浏览历史"),
            MenuItem(R.drawable.community, "社区"),
            MenuItem(R.drawable.person, "个人信息"),
            MenuItem(R.drawable.aboutus, "关于我们"),
            MenuItem(R.drawable.advice, "意见反馈"),
            MenuItem(R.drawable.preferences, "我的收藏"),
            MenuItem(R.drawable.history, "浏览历史"),
            MenuItem(R.drawable.community, "社区")
        )

        // 设置适配器
        recyclerView.adapter = MenuAdapter(menuItems)
    }
}