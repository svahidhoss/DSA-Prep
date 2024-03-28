package fortinet

class CountSubstrings {
    fun countSubstrings(inputString: String): Int {
        val mappedCharToDigit = mapOf(
            'a' to 1, 'b' to 1,
            'c' to 2, 'd' to 2, 'e' to 2,
            'f' to 3, 'g' to 3, 'h' to 3,
            'i' to 4, 'j' to 4, 'k' to 4,
            'l' to 5, 'm' to 5, 'n' to 5,
            'o' to 6, 'p' to 6, 'q' to 6,
            'r' to 7, 's' to 7, 't' to 7,
            'u' to 8, 'v' to 8, 'w' to 8,
            'x' to 9, 'y' to 9, 'z' to 9
        )

        var count = 0
        inputString.forEachIndexed { i, _ ->
            for (j in i + 1..inputString.length) {
                val substring = inputString.substring(i, j)
                var substringSum = 0
                substring.forEach {
                    substringSum += mappedCharToDigit[it]!!
                }
                if (substringSum % substring.length == 0) count++
            }
        }
        return count
    }

    fun countSubstrings2(input_str: String): Int {
        // Write your code here
        val mappedCharToDigit = mapOf(
            'a' to 1, 'b' to 1,
            'c' to 2, 'd' to 2, 'e' to 2,
            'f' to 3, 'g' to 3, 'h' to 3,
            'i' to 4, 'j' to 4, 'k' to 4,
            'l' to 5, 'm' to 5, 'n' to 5,
            'o' to 6, 'p' to 6, 'q' to 6,
            'r' to 7, 's' to 7, 't' to 7,
            'u' to 8, 'v' to 8, 'w' to 8,
            'x' to 9, 'y' to 9, 'z' to 9
        )

        val memo = mutableMapOf<String, Int>()
        // Create all substrings with their digit sum
        for (i in input_str.indices) {
            for (j in i + 1..input_str.length) {
                val substring = input_str.substring(i, j)
                var sum = 0
                substring.forEach {
                    sum += mappedCharToDigit[it]!!
                }
                memo[substring] = sum
            }
        }

        // TODO: memo can be removed if we do this within the above loop
        var result = 0
        memo.forEach { (k, v) ->
            if (v % k.length == 0) result++
        }

        return result
    }
}

fun main() {
    val sol = CountSubstrings()

    println(sol.countSubstrings("asdf"))
    println(sol.countSubstrings("bdh"))
    println(sol.countSubstrings("abcd"))
}