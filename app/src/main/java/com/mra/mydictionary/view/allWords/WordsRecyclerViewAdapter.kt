package com.mra.mydictionary.view.allWords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mra.mydictionary.databinding.AllWordsRecyclerviewRowBinding
import com.mra.mydictionary.model.WordEntity

class WordsRecyclerViewAdapter : RecyclerView.Adapter<AllWordsRecyclerViewHolder>() {

    private val dataList = mutableListOf<WordEntity>()
    fun submitData(data: List<WordEntity>) {
        val oldSize = dataList.size - 1
        dataList.addAll(data)

        notifyItemRangeInserted(oldSize, dataList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWordsRecyclerViewHolder {
        return AllWordsRecyclerViewHolder(
            AllWordsRecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: AllWordsRecyclerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}


class AllWordsRecyclerViewHolder(
    private val binding: AllWordsRecyclerviewRowBinding
) : ViewHolder(binding.root) {

    fun bind(model: WordEntity) {
        binding.word.text = model.word
        binding.wordDescription.text = model.description
    }
}