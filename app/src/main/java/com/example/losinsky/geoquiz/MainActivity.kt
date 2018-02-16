package com.example.losinsky.geoquiz

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {


    //Array of objects class Question (model)
    private val mQuestionBank = arrayOf (
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var mCurrentIndex: Int = -1
    private var mScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadText()
        //mCurrentIndex = 0 //in case of out of array

        // false button binding
        // instead of private Button mFalseButton [(Java)] correct_toast
        // mFalseButton = (Button)findViewById(R.id.false_button)
        // use this one [(Kotlin)]
        // no need to import Button class
        false_button.setOnClickListener {

            when (mQuestionBank[mCurrentIndex].mAnswerTrue)
            {
                true -> {
                    showIncorrectAnswer()
                    loadText()
                }
                false -> {
                    mScore++
                    showCorrectAnswer()
                    loadText()
                }
            }
            
            setScore(mScore)
        }

        // true button binding
        true_button.setOnClickListener {

           when (mQuestionBank[mCurrentIndex].mAnswerTrue)
           {
               true -> {
                   mScore++
                   showCorrectAnswer()
                   loadText()
               }

               false -> {
                   showIncorrectAnswer()
                   loadText()
               }
           }
            setScore(mScore)
        }

        // next button binding
        next_question_button.setOnClickListener {

            if (mCurrentIndex < mQuestionBank.size)
            {
                loadText()
            }
            else
            {
                playAgain()
            }

        }
    }

    private fun loadText (): Unit
    {
        if (mCurrentIndex < mQuestionBank.size -1)
        {
            mCurrentIndex++
            info_text.text = getString(mQuestionBank[mCurrentIndex].mTextResId)
        }
        else
        {
            mCurrentIndex++
            info_text.text = getString(R.string.out_of_questins)
            true_button.visibility = View.INVISIBLE
            false_button.visibility = View.INVISIBLE
            next_question_button.text = getString(R.string.play_again)
        }
    }

    private fun showCorrectAnswer()
    {
        // public static Toast makeText(Context context, int resId, int duration)
        Toast.makeText(this@MainActivity, R.string.correct_toast, Toast.LENGTH_SHORT).show()
    }

    private fun showIncorrectAnswer()
    {
        // public static Toast makeText(Context context, int resId, int duration)
        Toast.makeText(this@MainActivity, R.string.incorrect_answer, Toast.LENGTH_SHORT).show()
    }

    private fun setScore(score: Int)
    {
        score_points.text = score.toString()
    }

    private fun playAgain()
    {
        mCurrentIndex = -1
        mScore = 0
        loadText()
        setScore(mScore)
        true_button.visibility = View.VISIBLE
        false_button.visibility = View.VISIBLE
        next_question_button.text = getString(R.string.next_question)
    }
}