   package com.primetime.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.primetime.quizapp.databinding.ActivityResultBinding

   class ResultActivity : AppCompatActivity() {
    private lateinit var resultBinding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)


        resultBinding.tvName.text = intent.getStringExtra(Constants.USER_NAME)
        resultBinding.tvScore.text = "Your Score is ${intent.getIntExtra(Constants.CORRECT_ANSWER,0)} " +
                "out of ${intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)}"
        resultBinding.btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}