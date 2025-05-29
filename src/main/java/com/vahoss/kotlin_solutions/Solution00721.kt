import java.util.*

class Solution00721 {

    fun accountsMerge2(accounts: List<List<String>>): List<List<String>> {
        // 1. Initialize a graph and email-to-name map
        val graph = mutableMapOf<String, MutableSet<String>>()
        val emailToName = mutableMapOf<String, String>()

        // 2. Build the graph
        for (account in accounts) {
            val name = account[0]
            val firstEmail = account[1]
            for (i in 1 until account.size) {
                val email = account[i]
                emailToName[email] = name

                // Add edges in both directions
                graph.putIfAbsent(email, mutableSetOf())
                graph.putIfAbsent(firstEmail, mutableSetOf())
                graph[email]!!.add(firstEmail)
                graph[firstEmail]!!.add(email)
            }
        }

        // 3. DFS to find connected components
        val visited = mutableSetOf<String>()
        val result = mutableListOf<List<String>>()

        for (email in graph.keys) {
            if (email !in visited) {
                val component = mutableListOf<String>()

                // Start DFS
                val stack = Stack<String>()
                stack.push(email)
                while (stack.isNotEmpty()) {
                    val current = stack.pop()
                    if (current !in visited) {
                        visited.add(current)
                        component.add(current)
                        for (neighbor in graph[current]!!) {
                            if (neighbor !in visited) {
                                stack.push(neighbor)
                            }
                        }
                    }
                }

                // Add name and sorted emails to the result
                component.sort()
                val name = emailToName[component[0]]!!
                val merged = listOf(name) + component
                result.add(merged)
            }
        }

        // 4. Return the result
        return result
    }

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        // mapping of an email to the first account that contains it
        val graphMap = mutableMapOf<String, MutableSet<String>>()
        val emailToNameMap = mutableMapOf<String, String>()

        accounts.forEach { emails ->
            val name = emails[0]
            val firstEmail = emails[1]
            emailToNameMap[firstEmail] = name
            for (i in 1 until emails.size) {
                val email = emails[i]
                graphMap.putIfAbsent(firstEmail, mutableSetOf())
                graphMap[firstEmail]!!.add(email)

                graphMap.putIfAbsent(email, mutableSetOf())
                graphMap[email]!!.add(firstEmail)

                emailToNameMap[email] = name
            }
        }

        // DFS to find connected components
        val visited = mutableSetOf<String>()
        val result = mutableListOf<List<String>>()
        // Go over the graph
        graphMap.keys.forEach { email ->
            val currentResult = mutableListOf<String>()
            if (!visited.contains(email)) {
                dfs(email, graphMap, visited, currentResult)
                val name = emailToNameMap[currentResult[0]]!!
                // add the username and its emails back
                currentResult.sort()
                result.add(listOf(name) + currentResult)
            }
        }

        return result
    }

    private fun dfs(
        email: String,
        graphMap: MutableMap<String, MutableSet<String>>,
        visited: MutableSet<String>,
        current: MutableList<String>
    ) {
        if (email in visited) return
        visited.add(email)
        current.add(email)
        for (neighbor in graphMap[email]!!) {
            dfs(neighbor, graphMap, visited, current)
        }
    }
}

fun main() {
    val testCases = listOf(
        // Test Case 1
//        Pair(
//            listOf(
//                listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
//                listOf("John", "johnsmith@mail.com", "john00@mail.com"),
//                listOf("Mary", "mary@mail.com"),
//                listOf("John", "johnnybravo@mail.com")
//            ),
//            listOf(
//                listOf("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
//                listOf("Mary", "mary@mail.com"),
//                listOf("John", "johnnybravo@mail.com")
//            )
//        ),
//
//        // Test Case 2
//        Pair(
//            listOf(
//                listOf("Alex", "a@mail.com", "b@mail.com"),
//                listOf("Eli", "c@mail.com"),
//                listOf("Alex", "b@mail.com", "d@mail.com"),
//                listOf("Eli", "e@mail.com", "c@mail.com")
//            ),
//            listOf(
//                listOf("Alex", "a@mail.com", "b@mail.com", "d@mail.com"),
//                listOf("Eli", "c@mail.com", "e@mail.com")
//            )
//        ),
//
//        // Test Case 3
//        Pair(
//            listOf(
//                listOf("Anna", "anna1@mail.com"),
//                listOf("Anna", "anna2@mail.com"),
//                listOf("Anna", "anna3@mail.com")
//            ),
//            listOf(
//                listOf("Anna", "anna1@mail.com"),
//                listOf("Anna", "anna2@mail.com"),
//                listOf("Anna", "anna3@mail.com")
//            )
//        ),
//
//        // Test Case 4
//        Pair(
//            listOf(
//                listOf("Tom", "a@mail.com", "b@mail.com"),
//                listOf("Tom", "b@mail.com", "c@mail.com"),
//                listOf("Tom", "c@mail.com", "d@mail.com")
//            ),
//            listOf(
//                listOf("Tom", "a@mail.com", "b@mail.com", "c@mail.com", "d@mail.com")
//            )
//        ),

        // Test Case 5
        Pair(
            listOf(
                listOf("Alice", "x@mail.com"),
                listOf("Bob", "x@mail.com")
            ),
            listOf(
                listOf("Alice", "x@mail.com") // name may vary depending on merge logic
            )
        ),

        // Test Case 6: Empty input
        Pair(
            emptyList<List<String>>(),
            emptyList<List<String>>()
        ),

        // Test Case 7
        Pair(
            listOf(
                listOf("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co"),
                listOf("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co"),
                listOf("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co"),
                listOf("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co"),
                listOf("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co"),
            ),
            listOf(
                listOf("Alex", "Alex0@m.co", "Alex4@m.co", "Alex5@m.co"),
                listOf("Ethan", "Ethan0@m.co", "Ethan3@m.co"),
                listOf("Gabe", "Gabe0@m.co", "Gabe2@m.co", "Gabe3@m.co", "Gabe4@m.co"),
                listOf("Kevin", "Kevin2@m.co", "Kevin4@m.co")
            )
        ),


        )

    for ((index, test) in testCases.withIndex()) {
        val (input, expected) = test
        println("🔹 Test Case ${index + 1}")
        println("Input:")
        input.forEach { println(it) }

        val s = Solution00721()
        val actual = s.accountsMerge(input)

        println("Expected:")
        expected.forEach { println(it.sorted()) }

        println("Actual:")
        actual.forEach { println(it.sorted()) }

        val normalizedActual = actual.map { it.sorted() }.sortedBy { it.firstOrNull() }
        val normalizedExpected = expected.map { it.sorted() }.sortedBy { it.firstOrNull() }

        val result = normalizedActual == normalizedExpected
        println("Match: $result  ${if (result) "✅ Passed" else "❌ Failed"}")
        println("=".repeat(50))
    }
}

