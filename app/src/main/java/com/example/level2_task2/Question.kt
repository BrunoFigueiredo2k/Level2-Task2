package com.example.level2_task2

data class Question(
    var questionText: String,
    var answer: String
){
    companion object {
        val QUESTION_TEXT = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
        )
        val QUESTION_ANSWER = arrayOf(
            "False",
            "True",
            "True",
            "True"
        )
    }
}