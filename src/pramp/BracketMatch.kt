package pramp

fun bracketMatch(text: String): Int {
    var count = 0
    var result = 0
    text.forEach { c ->
        if (c == '(') count++
        else count--

        if (count < 0) {
            result++
            count = 0
        }
    }

    return result + count
}