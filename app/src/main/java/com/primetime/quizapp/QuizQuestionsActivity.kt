package com.primetime.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.primetime.quizapp.databinding.ActivityQuizQuestionsBinding
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var quizBinding : ActivityQuizQuestionsBinding

    private var mCurrentPosition:Int=1
    private var mQuestionList:ArrayList<Questions>?=null
    private var mSelectedOptionPosition:Int=0
    private var mCorrectAnswer:Int=0
    private var mUserName:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        quizBinding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(quizBinding.root)

        //retrieving  username
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()

        setQuestions()

        quizBinding.tvOptionOne.setOnClickListener(this)
        quizBinding.tvOptionTwo.setOnClickListener(this)
        quizBinding.tvOptionThree.setOnClickListener(this)
        quizBinding.tvOptionFour.setOnClickListener(this)
        quizBinding.btnSubmit.setOnClickListener(this)




    }

    private fun setQuestions(){
        val question:Questions? = mQuestionList!![mCurrentPosition-1]
        defaultOptioView()

        if(mCurrentPosition == mQuestionList!!.size){
            quizBinding.btnSubmit.text = "FINSIH"
        }else{
            quizBinding.btnSubmit.text = "SUBMIT"
        }

        quizBinding.progessBar.progress = mCurrentPosition
        quizBinding.tvProgress.text = "$mCurrentPosition/${quizBinding.progessBar.max}"

        quizBinding.tvQuestion.text = question!!.question
        quizBinding.ivImage.setImageResource(question.image)

        quizBinding.tvOptionOne.text = question.optionOne
        quizBinding.tvOptionTwo.text = question.optionTwo
        quizBinding.tvOptionThree.text = question.optionThree
        quizBinding.tvOptionFour.text = question.optionFour
    }
    private fun defaultOptioView(){
        val options = ArrayList<TextView>()
        options.add(0,quizBinding.tvOptionOne)
        options.add(1,quizBinding.tvOptionTwo)
        options.add(2,quizBinding.tvOptionThree)
        options.add(3,quizBinding.tvOptionFour)

        for (option in options){
            option.apply {
                setTextColor(Color.parseColor("#7A8089"))
                typeface = Typeface.DEFAULT
            }.also {
                it.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionView(quizBinding.tvOptionOne,1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(quizBinding.tvOptionTwo,2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(quizBinding.tvOptionThree,3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(quizBinding.tvOptionFour,4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestions()
                        }else->{
                            Intent(this,ResultActivity::class.java).let {
                                it.putExtra(Constants.USER_NAME,mUserName)
                                it.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswer)
                                it.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                                startActivity(it)
                                finish()
                            }

                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question!!.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        quizBinding.btnSubmit.text = "FINSIH"
                    }else{
                        quizBinding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 -> {
                quizBinding.tvOptionOne.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 -> {
                quizBinding.tvOptionTwo.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 -> {
                quizBinding.tvOptionThree.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 -> {
                quizBinding.tvOptionFour.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptioView()
        mSelectedOptionPosition = selectedOptionNum
        tv.apply {
            setTextColor(Color.parseColor("#363A43"))
            setTypeface(this.typeface,Typeface.BOLD)
        }.also {
            it.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
        }
    }
}