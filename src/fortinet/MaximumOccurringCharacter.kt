package fortinet

class MaximumOccurringCharacter {

    fun maximumOccurringCharacter(text: String): Char {
        // Write your code here
        // ASCII size for all standard characters, including a-z, A-Z, 0-9
        val counts = IntArray(128)
        // Need this to display to consider and return the char that appears first
        val firstOccurrence = IntArray(128) { -1 }

        var maxCount = 0
        var firstOccurringIndex = Int.MAX_VALUE
        var maxChar: Char = text[0]
        // Count the frequency of each character
        text.forEachIndexed { i, char ->
            if (firstOccurrence[char.code] < 0) firstOccurrence[char.code] = i
            if (++counts[char.code] > maxCount && firstOccurrence[char.code] < firstOccurringIndex) {
                maxCount = counts[char.code]
                maxChar = char
                firstOccurringIndex = firstOccurrence[char.code]
            }
        }

        return maxChar
    }
}

fun main() {
    val sol = MaximumOccurringCharacter()
    println(sol.maximumOccurringCharacter("helloworld"))
    println(sol.maximumOccurringCharacter("abbbaacc"))
}
