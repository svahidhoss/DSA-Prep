package com.vahoss.amazon

fun minimumKeypresses(s: String): Int {
    var result = 0
    val map = mutableMapOf<Char, Int>()
    s.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }

    val sortedList = map.entries.sortedByDescending { it.value }
    val singlePressLimit = 9
    val doublePressLimit = 18
    val singlePressMultiplier = 1
    val doublePressMultiplier = 2
    val triplePressMultiplier = 3

    sortedList.forEachIndexed { i, charRep ->
        result += when {
            i < singlePressLimit -> charRep.value * singlePressMultiplier
            i < doublePressLimit -> charRep.value * doublePressMultiplier
            else -> charRep.value * triplePressMultiplier
        }
    }

    return result
}

fun minimumKeypresses2(s: String): Int {
    var result = 0
    val map = mutableMapOf<Char, Int>()
    s.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }

    val sortedList = map.entries.sortedBy { -it.value }
    sortedList.forEachIndexed { i, charRep ->
        result += if (i < 9) charRep.value
        else if (i < 18) charRep.value * 2
        else charRep.value * 3
    }

    return result
}

fun main() {
    println(minimumKeypresses("apple"))
    println(minimumKeypresses("abcdefghijkl"))
}