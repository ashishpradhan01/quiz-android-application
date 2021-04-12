package com.primetime.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.primetime.quizapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)



        mainBinding.btnStart.setOnClickListener {
            val name = mainBinding.nameET.text.toString()
            if (name.isEmpty()){
                mainBinding.nameET.error="Please enter your name"
            }
            else {
                Intent(this, QuizQuestionsActivity::class.java).let {
                    it.putExtra(Constants.USER_NAME, name)
                    startActivity(it)
                    finish()
                }
            }
        }

    }
}