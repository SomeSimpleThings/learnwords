package com.somethingsimple.learnwords.ui.words.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.databinding.WordItemBinding

class WordAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<Word>
) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

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
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.tvWord.text = word.text
                binding.tvDescription.text =
                    word.meanings?.get(0)?.translation?.text

                itemView.setOnClickListener { openInNewWindow(word) }
            }
        }
    }

    private fun openInNewWindow(listItemData: Word) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Word)
    }
}
