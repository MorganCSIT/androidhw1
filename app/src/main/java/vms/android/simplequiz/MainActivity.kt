package vms.android.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questionViewModel: QuestionViewModel by lazy {ViewModelProvider(this).get(QuestionViewModel:: class.java)}
    private val TAG = "MainActivity"
    var correctAnswers = 0
    var incorrectAnswers = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()

        yesButton.setOnClickListener {
            checkAnswer(true)
        }

        noButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            questionViewModel.moveToNextQuestion()
            nextQuestion()
        }

        displayedQuestion.setOnClickListener {
            questionViewModel.moveToNextQuestion()
            nextQuestion()
        }

        previousButton.setOnClickListener {
            questionViewModel.moveToPreviousQuestion()
            updateQuestion()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun nextQuestion() {
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionViewModel.currentQuestionText
        displayedQuestion.setText(questionTextResId)
    }

    private fun showQuizResult() {
        val totalQuestions = questionViewModel.questionBank.size
        val score = ((correctAnswers.toDouble() / totalQuestions) * 100).toInt()
        Toast.makeText(this, "You scored: $score%", Toast.LENGTH_SHORT).show()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionViewModel.currentQuestionAnswer

        if (questionViewModel.userAnswered) {
            Toast.makeText(this, "You have already answered this question", Toast.LENGTH_SHORT).show()
        } else {
            if (userAnswer == correctAnswer) {
                correctAnswers++
            } else {
                incorrectAnswers++
            }
            questionViewModel.userAnswered = true

            if (questionViewModel.currentIndex == questionViewModel.questionBank.size - 1) {
                showQuizResult()
            }
        }
    }


}