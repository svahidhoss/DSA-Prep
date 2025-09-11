package com.vahoss.kotlin_solutions

import kotlin.text.iterator

data class TrieNode(
    var isEnd: Boolean = false,
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
)

class Trie() {
    // Root is not linked to any char
    var root = TrieNode()

    fun insert(word: String) {
        var currentNode: TrieNode? = root
        for (c in word) {
            // if any of the current node's children is linked to char c,
            // use that or create a new TrieNode
            val nextNode = currentNode?.children?.getOrPut(c) {
                TrieNode()
            }
            currentNode = nextNode
        }
        // end with the word
        currentNode?.isEnd = true
    }

    fun search(word: String): Boolean {
        return findWord(word)?.isEnd ?: false
    }

    fun startsWith(prefix: String): Boolean {
        return findWord(prefix) != null
    }

    private fun findWord(word: String): TrieNode? {
        var node = root
        for (c in word) {
            if (node.children.containsKey(c)) {
                node = node.children[c]!!
            } else return null
        }

        return node
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
fun main() {
    val obj = Trie()
    var word = "cat"
    obj.insert(word)
    word = "car"
    obj.insert(word)
    println(obj.search(word))
    println(obj.startsWith("ca"))
}
