package com.mra.mydictionary.utils

interface BaseDataModelState<T> {
    val data: T?
    val errorMessage: String?
}