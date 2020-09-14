package com.example.level2_task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.example.level2_task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.question.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init views function here
        initViews()
    }

    private fun initViews(){
        // Initialize Recycler view with grid layout and border at bottom of each item
        binding.rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        // Attach the ItemTouchHelper to the recyclerView
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        // Populate the places list and notify the data set has changed.
        for (i in Question.QUESTION_TEXT.indices) {
            questions.add(Question(Question.QUESTION_TEXT[i], Question.QUESTION_ANSWER[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper enables left and right swipe to check for true or false to answer
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                // Defining actual answers by each question (as position)
                val questionAnswer = when (position){
                    0 -> false
                    1 -> true
                    2 -> true
                    3 -> true
                    else -> true
                }

                // Check if user answer is equal to answer of item
                if (direction == ItemTouchHelper.RIGHT && questionAnswer || direction == ItemTouchHelper.LEFT && !questionAnswer) {
                    // Delete from list if answer is true
                    questions.removeAt(position)
                } else {
                    // Snackbar message incorrect answer
                    Snackbar.make(tvQuestion, "The answer is incorrect, so it will not be removed from list.", Snackbar.LENGTH_SHORT).show()
                }

                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}