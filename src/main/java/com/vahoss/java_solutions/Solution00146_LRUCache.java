package com.vahoss.java_solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution00146_LRUCache {
    int capacity;

    Deque<Integer> cache;
    Map<Integer, Integer> map;

    public Solution00146_LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new ArrayDeque<>(capacity);
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            // Move accessed elements to the front of the cache
            cache.remove(key);
            cache.addFirst(key);
            return value;
        }
        // not found
        return -1;
    }

    public void put(int key, int value) {
        // If already existing, remove the key first
        if (map.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() >= capacity) {
            // remove the last element that was added
            int keyToRemove = cache.removeLast();
            map.remove(keyToRemove);
        }
        // add new item to the front of the cache
        map.put(key, value);
        cache.addFirst(key);
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        Solution00146_LRUCache cache = new Solution00146_LRUCache(2);

        System.out.println("Put (1,1)");
        cache.put(1, 1);
        System.out.println("Put (2,2)");
        cache.put(2, 2);

        System.out.println("Get 1, Expected: 1, Actual: " + cache.get(1));

        System.out.println("Put (3,3) - should evict key 2");
        cache.put(3, 3);

        System.out.println("Get 2, Expected: -1, Actual: " + cache.get(2));

        System.out.println("Put (4,4) - should evict key 1");
        cache.put(4, 4);

        System.out.println("Get 1, Expected: -1, Actual: " + cache.get(1));
        System.out.println("Get 3, Expected: 3, Actual: " + cache.get(3));
        System.out.println("Get 4, Expected: 4, Actual: " + cache.get(4));
    }
}