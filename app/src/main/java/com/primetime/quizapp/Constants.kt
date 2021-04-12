package com.primetime.quizapp

object Constants {

    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_question"
    const val CORRECT_ANSWER:String = "correct_answer"

    private const val QUESTIONS = "What country does this flag belong to?"
    fun getQuestions():ArrayList<Questions>{
        val questionsList = arrayListOf<Questions>()

        val que1 = Questions(1, QUESTIONS,R.drawable.ic_flag_of_argentina,
            "Argentina","Belgium",
            "Australia","Fiji",1)
        val que2 = Questions(2, QUESTIONS,R.drawable.ic_flag_of_australia,
            "Denmark","Germany",
            "Australia","Kuwait",3)
        val que3 = Questions(3, QUESTIONS,R.drawable.ic_flag_of_belgium,
            "Kuwait","Belgium",
            "India","Germany",2)
        val que4 = Questions(4, QUESTIONS,R.drawable.ic_flag_of_brazil,
            "Argentina","Belgium",
            "Fiji","Brazil",4)
        val que5 = Questions(5, QUESTIONS,R.drawable.ic_flag_of_india,
            "India","New Zealand",
            "Denmark","Germany",1)

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        return questionsList
    }
}