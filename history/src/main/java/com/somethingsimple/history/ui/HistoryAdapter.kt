package com.somethingsimple.history.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.somethingsimple.history.databinding.WordItemBinding
import com.somethingsimple.model.vo.Word

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var data: List<Word> = arrayListOf()

    fun setData(data: List<Word>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Word) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.tvWord.text = data.text
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "on click: ${data.text}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
