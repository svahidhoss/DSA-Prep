package com.vahoss

class Fibonacci {

    fun recursiveFibonacci(n: Int): Long {
        if (n <= 0) return 0
        if (n == 1) return 1

        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2)
    }

}