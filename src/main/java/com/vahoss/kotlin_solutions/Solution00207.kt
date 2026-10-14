package com.vahoss.kotlin_solutions

class Solution00207 {

    /**
     * Kahn's algorithm (BFS topological sort). count[c] is how many prerequisites of c
     * are still untaken, and dependents[b] lists the courses waiting on b. Start with every
     * course whose count is 0; taking a course lowers the count of each course waiting on
     * it, and a course joins the queue when its count reaches 0. Courses in a cycle never
     * reach 0, so the answer is true only if every count ends at 0.
     * (Adding each taken course to a list gives the course order for #210.)
     *
     * Time: O(V + E). Each course is queued once and each edge is walked once.
     * Space: O(V + E) for dependents, O(V) for count and the queue. No recursion.
     * (V = numCourses, E = prerequisites.size)
     */
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        // graph that maps each course to the courses waiting on it
        val dependents = Array(numCourses) { mutableListOf<Int>() }
        // default is 0
        val count = IntArray(numCourses)

        for ((a, b) in prerequisites) {
            dependents[b].add(a)
            count[a]++
        }

        val queue = ArrayDeque<Int>()
        // add all course with no preq.
        queue.addAll(count.indices.filter { count[it] == 0 })
        // BFS traversing of dependants
        while (queue.isNotEmpty()) {
            val courseId = queue.removeFirst()
            for (c in dependents[courseId]) {
                count[c]--
                // add c to the queue
                if (count[c] == 0) queue.add(c)
            }
        }

        return count.all { it == 0 }
    }

    /**
     * Array-based adjacency list plus DFS with three states per course: unvisited,
     * on the current path (onPath), and fully checked (visited). Stepping onto a course
     * that is on the current path means a cycle. A course is taken off the path and
     * marked visited once all its prerequisites check out, so it is never walked twice.
     *
     * Time: O(V + E). Each course is expanded once and each edge is walked once.
     * Space: O(V + E) for the graph, O(V) for onPath/visited and for the recursion stack.
     * (V = numCourses, E = prerequisites.size)
     */
    fun canFinish0(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array(numCourses) { mutableListOf<Int>() }

        // Build graph: edge from a → b means a depends on b
        for ((a, b) in prerequisites) {
            graph[a].add(b)
        }

        // fully processed/visted nodes (safe ones)
        val visited = BooleanArray(numCourses)
        // current DFS path (for cycle detection)
        val onPath = BooleanArray(numCourses)

        // DFS local fn to detect cycles
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

    data class Course(val id: Int, val preqs: MutableList<Course> = mutableListOf())

    /**
     * Same DFS as [canFinish0], but the graph is a map of Course objects built with
     * getOrPut, and path/visitedCourses are Sets of ids. Only courses that appear in
     * at least one prerequisite pair are created; courses with no edges can't be part
     * of a cycle, so they are never visited and numCourses is not needed.
     *
     * Time: O(E) on average (hash operations are O(1) on average; at most 2E courses
     * exist in the map, each expanded once and each edge walked once).
     * Space: O(E) for the Course objects and their preqs lists, O(V') for the two Sets
     * and the recursion stack, where V' <= 2E is the number of courses in the map.
     * Uses more memory per course than [canFinish0] (boxed Ints, hashing, objects).
     */
    fun canFinish1(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val map = mutableMapOf<Int, Course>()
        for (pre in prerequisites) {
            // create the courses if they don't exist already
            val course0 = map.getOrPut(pre[0]) { Course(pre[0]) }
            val course1 = map.getOrPut(pre[1]) { Course(pre[1]) }
            course0.preqs.add(course1)
        }

        // courses fully checked and known to be safe (never cleared)
        val visitedCourses = mutableSetOf<Int>()
        // courses on the DFS trail we are walking right now
        val path = mutableSetOf<Int>()
        // Courses with no edges can't be part of a cycle, so only the ones in the map matter
        for (course in map.values) {
            if (!visit(course, path, visitedCourses)) return false
        }
        return true
    }

    private fun visit(
        course: Course,
        path: MutableSet<Int>,
        visitedCourses: MutableSet<Int>
    ): Boolean {
        // Already fully checked: safe, skip.
        if (course.id in visitedCourses) return true
        // Already on the current trail: we walked in a circle, so this is a cycle.
        if (!path.add(course.id)) return false

        for (preq in course.preqs) {
            if (!visit(preq, path, visitedCourses)) return false
        }

        // Backtrack: leave the trail, and remember this course as safe.
        path.remove(course.id)
        // Mark this course as visited and safe
        visitedCourses.add(course.id)
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
        /**
         * test 6: multiple paths, but no cycle
         * Courses: 3 needs 1 and 2, 1 needs 0, and 2 needs 0.
         *    3
         *   / \
         *  1  2
         *  \ /
         *   0
         * There's no cycle here. Course 0 is simply a prerequisite of two different courses.
         */
        Pair(
            Pair(4, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))),
            true
        )
    )

    val solution = Solution00207()

    testCases.forEachIndexed { index, (input, expected) ->
        val (numCourses, prereqs) = input
        val result = solution.canFinish(numCourses, prereqs)
        println(
            "Test ${index + 1}: ${if (result == expected) "✅ Passed" else "❌ Failed"}" +
                    " (Expected: $expected, Got: $result)"
        )
    }
}

