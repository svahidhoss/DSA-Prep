package com.vahoss.kotlin_solutions

class Solution00207 {
    data class Course(val courseNum: Int, val prerequisites: MutableList<Course> = mutableListOf())

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val courses = Array(numCourses) { Course(it) }
        // add all prerequisites
        prerequisites.forEach { (a, b) ->
            courses[a].prerequisites.add(courses[b])
        }
        val coursesPreReqState = BooleanArray(numCourses) { courseNum ->
            // if no pre-req make them true
            courses[courseNum].prerequisites.isEmpty()
        }
        // for finding cycles:
        val onPath = BooleanArray(numCourses)

        // DFS
        courses.forEach { course ->
            visitCourse(course, courses, coursesPreReqState, onPath)
        }

        return coursesPreReqState.all { it }
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
            if(!visitCourse(it, courses, visited, onPath)) return false
        }

        // The DFS for this course is done — we're no longer "on the current path"
        onPath[course.courseNum] = false
        // course is valid
        visited[course.courseNum] = true
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
        val result = solution.canFinish(numCourses, prereqs)
        println("Test $index: ${if (result == expected) "✅ Passed" else "❌ Failed"} (Expected: $expected, Got: $result)")
    }
}

