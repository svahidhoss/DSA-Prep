package pramp

import java.util.*

data class WordsCount(val word: String, val count: Int = 0)

fun wordCountEngine(document: String?): Array<Array<String>> {
    // your code goes here
    // Cleaning the string: case-insensitive, remove anything except a-z and spaces
    // Regex pattern to match anything that is not an alphabet or a space
    val regex = "[^A-Za-z\\s]".toRegex()
    // Replace all occurrences of the matched pattern with an empty string
    val cleanDoc = document?.lowercase()?.replace(regex, "")

    // Save them in a hash map of String to counts
    val words = cleanDoc?.split(" ") ?: emptyList()

    val wordsMap = mutableMapOf<String, Int>()
    words.forEach {
        if (!wordsMap.contains(it)) wordsMap[it] = 1
        else wordsMap[it] = wordsMap[it]!! + 1
    }
    // sort them based on their counts, or we could use heap structure
    val sortedList = wordsMap.entries.sortedByDescending { it.value }
    val result = mutableListOf<Array<String>>()
    sortedList.forEach {
        result.add(arrayOf(it.key, it.value.toString()))
    }

    return result.toTypedArray()
}

fun wordCountEngineImproved(document: String?): Array<Array<String>> {
    // Return an empty array immediately if the document is null
    if (document == null) return arrayOf()

    // Cleaning the string: case-insensitive, remove anything except a-z and spaces
    val cleanDoc = document.lowercase().replace("[^a-z\\s]+".toRegex(), "")

    // Split the cleaned document into words, filtering out empty strings
    val words = cleanDoc.split("\\s+".toRegex()).filter { it.isNotBlank() }

    // Use groupBy and mapValues to count occurrences of each word
    val wordsMap = words.groupingBy { it }.eachCount()

    // Sort the map entries by their counts in descending order
    val sortedList = wordsMap.entries.sortedByDescending { it.value }

    // Convert the sorted list to the desired format
    val result = sortedList.map { arrayOf(it.key, it.value.toString()) }.toTypedArray()

    return result
}


fun main() {
    val result = wordCountEngine("Practice makes perfect. you'll only get Perfect by practice. just practice!")
    println(Arrays.toString(result))
}