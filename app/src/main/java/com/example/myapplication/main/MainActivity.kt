package com.example.myapplication.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.main.adapter.ViewpagerFragmentAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.card.MaterialCardView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewPagerAdapter: ViewpagerFragmentAdapter by lazy {
        ViewpagerFragmentAdapter(this)
    }

    lateinit var clList: List<ConstraintLayout>

    private fun initCardBg() {
        for (card in clList) {
            card.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.vp.adapter = viewPagerAdapter
        binding.vp.isUserInputEnabled = false

        binding.home1.setOnClickListener {
            initCardBg()
            binding.cl1.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
            binding.vp.setCurrentItem(0, false)
        }

        binding.home2.setOnClickListener {
            initCardBg()
            binding.cl2.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
            binding.vp.setCurrentItem(1, false)
        }

        binding.home3.setOnClickListener {
            initCardBg()
            binding.cl3.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
            binding.vp.setCurrentItem(2, false)
        }

        binding.home4.setOnClickListener {
            initCardBg()
            binding.cl4.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
            binding.vp.setCurrentItem(3, false)
        }

        with(binding) {
            clList = listOf(cl1, cl2, cl3, cl4)
        }

        FirebaseApp.initializeApp(this)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(object : OnCompleteListener<String?> {
                fun onSuccess(token: String?) {
                    Log.d("MessageToken", "$token")
                }

                override fun onComplete(task: Task<String?>) {
                    if (!task.isSuccessful) {
                        Log.w("MessageToken", "토큰 생성 실패", task.exception)
                        return
                    }
                    // 새로운 토큰 생성 성공 시
                    val token = task.result
                    Log.d("MessageToken", "$token")
                }
            })


    }
}