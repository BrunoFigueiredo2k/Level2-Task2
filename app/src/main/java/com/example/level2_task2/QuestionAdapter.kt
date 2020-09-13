package com.example.level2_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.level2_task2.databinding.QuestionBinding

class QuestionAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    /** Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        )
    }

    /** Returns the size of the list */
    override fun getItemCount(): Int {
        return questions.size
    }

    /** Called by RecyclerView to display the data at the specified position. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(questions[position])
    }

    // Fill data of single Question object with question
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = QuestionBinding.bind(itemView)

        fun bind(question: Question){
            binding.tvQuestion.text = question.questionText
        }
    }
}