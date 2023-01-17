package com.mra.mydictionary.view.allWords

enum class FilterType {
    NEWEST, OLDEST, A_TO_Z, Z_TO_A

}

fun toFilterType(name: String) = when(name.uppercase()) {
    FilterType.NEWEST.name -> FilterType.NEWEST
    FilterType.OLDEST.name -> FilterType.OLDEST
    FilterType.A_TO_Z.name -> FilterType.A_TO_Z
    FilterType.Z_TO_A.name -> FilterType.Z_TO_A
    else -> FilterType.NEWEST
}
