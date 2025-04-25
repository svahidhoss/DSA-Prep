package java.pramp

fun bracketMatch2(text: String): Int {
    var count = 0
    var result = 0

    text.forEach {
        if (it == '(')
            count++
        else {
            if (count > 0) count--  // Match the current closing parenthesis with an unmatched opening one
            else result++  // Increment result for each unmatched closing parenthesis
        }
    }
    return result + count
}

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