package com.mra.mydictionary.view.allWords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mra.mydictionary.R
import com.mra.mydictionary.databinding.AllWordsRecyclerviewRowBinding
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.backgroundBuilder
import java.util.*

class WordsRecyclerViewAdapter : RecyclerView.Adapter<AllWordsRecyclerViewHolder>() {

    private val dataList = mutableListOf<WordEntity>()
    fun submitData(data: List<WordEntity>) {
        dataList.apply {
            val oldSize = size - 1
            clear()
            notifyItemRangeRemoved(0, oldSize)
            addAll(data)
            notifyItemRangeInserted(0, size - 1)
        }
    }

    fun addData(data: List<WordEntity>) {
        val oldSize = dataList.size - 1
        val newItemNumber = data.size - dataList.size
        dataList.addAll(data.subList(oldSize + 1, data.size - 1))

        notifyItemRangeInserted(oldSize + 1, newItemNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWordsRecyclerViewHolder {
        return AllWordsRecyclerViewHolder(
            AllWordsRecyclerviewRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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
        binding.id.text = "${model.id} = $layoutPosition"
        binding.word.text = model.word
        binding.wordDescription.text = model.description
        binding.date.text = Date(model.creationDate).toString()
    }
}