package com.mra.mydictionary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word: String,
    val description: String? = null
)
