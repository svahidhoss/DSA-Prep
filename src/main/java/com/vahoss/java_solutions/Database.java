package com.vahoss.java_solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Database {

    /**
     * Stack to handle nested transactions
     */
    private Stack<Map<String, String>> transactions;

    /**
     * Main database storage
     */
    private Map<String, String> currentData;

    public Database() {
        currentData = new HashMap<>();
        transactions = new Stack<>();
    }

    public void set(String key, String value) {
        if (!transactions.isEmpty()) {
            transactions.peek().put(key, value);
        } else {
            currentData.put(key, value);
        }
        System.out.println("OK");
    }

    public void get(String key) {
        String value = null;
        if (!transactions.isEmpty()) {
            value = transactions.peek().get(key);
        }
        if (value == null) {
            value = currentData.get(key);
        }
        System.out.println(value != null ? value : "NULL");
    }

    public void delete(String key) {
        if (!transactions.isEmpty()) {
            transactions.peek().put(key, null);
        } else {
            currentData.remove(key);
        }
        System.out.println("OK");
    }

    public void begin() {
        transactions.push(new HashMap<>());
        System.out.println("OK");
    }

    public void commit() {
        if (transactions.isEmpty()) {
            System.out.println("NO TRANSACTION");
            return;
        }
        Map<String, String> topTransaction = transactions.pop();
        if (transactions.isEmpty()) {
            currentData.putAll(topTransaction);
        } else {
            transactions.peek().putAll(topTransaction);
        }
        System.out.println("OK");
    }

    public void rollback() {
        if (transactions.isEmpty()) {
            System.out.println("NO TRANSACTION");
            return;
        }
        transactions.pop();
        System.out.println("OK");
    }
}
