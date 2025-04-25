package main.java.pramp

fun flattenDictionary(dict: Map<String, Any>): Map<String, String> {
    val result = mutableMapOf<String, String>()
    flattenDictionaryHelper("", dict, result)
    return result
}

fun flattenDictionaryHelper(initialKey: String, dict: Map<String, Any>, result: MutableMap<String, String>) {
    for ((key, value) in dict) {
        val newKey = when {
            initialKey.isEmpty() -> key
            key.isEmpty() -> initialKey
            else -> "$initialKey.$key"
        }

        if (value is Map<*, *>) {
            // Recursive call with casting to the correct type
            @Suppress("UNCHECKED_CAST")
            (flattenDictionaryHelper(newKey, value as Map<String, Any>, result))
        } else {
            // Base case: value is a String
            result[newKey] = value.toString()
        }
    }
}

fun main() {
    val dict = mapOf(
        "Key1" to "1",
        "Key2" to mapOf(
            "a" to "2",
            "b" to "3",
            "c" to mapOf(
                "d" to "3",
                "e" to mapOf(
                    "" to "1"
                )
            )
        )
    )

    println(flattenDictionary(dict))
}