package com.vahoss.kotlin_solutions

class Solution00207 {
    data class Course(val courseNum: Int, val prerequisites: MutableList<Course> = mutableListOf())

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val courses = Array(numCourses) { Course(it) }
        // add all prerequisites
        prerequisites.forEach { (a, b) ->
            courses[a].prerequisites.add(courses[b])
        }

        // keeps track of fully processed nodes
        val visited = BooleanArray(numCourses) { courseNum ->
            // if no pre-req make them true
            courses[courseNum].prerequisites.isEmpty()
        }
        // current DFS path (for finding cycles):
        val onPath = BooleanArray(numCourses)

        // DFS: Go over all nodes because they may not all be connected
        courses.forEach { course ->
            visitCourse(course, courses, visited, onPath)
        }

        return visited.all { it }
    }

    private fun visitCourse(
        course: Course,
        courses: Array<Course>,
        visited: BooleanArray,
        onPath: BooleanArray
    ): Boolean {
        if (visited[course.courseNum]) return true
        // Cycle found
        if (onPath[course.courseNum]) return false
        onPath[course.courseNum] = true

        // check the visited state of all pre-reqs
        course.prerequisites.forEach {
            if (!visitCourse(it, courses, visited, onPath)) return false
        }

        // The DFS for this course is done — we're no longer "on the current path"
        onPath[course.courseNum] = false
        // course is valid
        visited[course.courseNum] = true
        return true
    }

    fun canFinish2(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array(numCourses) { mutableListOf<Int>() }

        // Build graph: edge from a → b means a depends on b
        for ((a, b) in prerequisites) {
            graph[a].add(b)
        }

        // fully processed nodes (safe)
        val visited = BooleanArray(numCourses)
        // current DFS path (for cycle detection)
        val onPath = BooleanArray(numCourses)

        // DFS to detect cycles
        fun dfs(course: Int): Boolean {
            if (onPath[course]) return false       // cycle found!
            if (visited[course]) return true       // already verified: safe

            onPath[course] = true

            for (prereq in graph[course]) {
                if (!dfs(prereq)) return false     // propagate cycle failure
            }

            onPath[course] = false  // backtrack from the course
            visited[course] = true  // mark as visited
            return true
        }

        // Run DFS from every course (in case of disconnected components)
        for (course in 0 until numCourses) {
            if (!dfs(course)) return false
        }

        return true
    }

}

fun main() {
    val testCases = listOf(
        // test 1: simple linear dependency, should be possible
        Pair(
            Pair(2, arrayOf(intArrayOf(1, 0))),
            true
        ),
        // test 2: cycle 0 -> 1 -> 0
        Pair(
            Pair(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))),
            false
        ),
        // test 3: multiple independent courses
        Pair(
            Pair(3, arrayOf(intArrayOf(1, 0))),
            true
        ),
        // test 4: self-loop (course requires itself)
        Pair(
            Pair(1, arrayOf(intArrayOf(0, 0))),
            false
        ),
        // test 5: larger cycle
        Pair(
            Pair(4, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 1))),
            false
        ),
        // test 6: multiple paths, but no cycle
        Pair(
            Pair(4, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))),
            true
        )
    )
    val solution = Solution00207()
    testCases.forEachIndexed { index, (input, expected) ->
        val (numCourses, prereqs) = input
        val result = solution.canFinish2(numCourses, prereqs)
        println("Test $index: ${if (result == expected) "✅ Passed" else "❌ Failed"} (Expected: $expected, Got: $result)")
    }
}

