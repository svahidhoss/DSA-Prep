package java_solutions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution00133 {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // Use this to find nodes that are already cloned
        var map = new HashMap<Integer, Node>();
        map.put(node.val, new Node(node.val));
        // For BFS
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            var polledNode = queue.poll();
            // Get the existing node from the map
            var newNode = map.get(polledNode.val);

            for (var neighbor : polledNode.neighbors) {
                if (!map.containsKey(neighbor.val)) {
                    map.put(neighbor.val, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                newNode.neighbors.add(map.get(neighbor.val));
            }
        }

        // result has only one neighbor
        return map.get(node.val);
    }

    public static void main(String[] args) {
        var solution = new Solution00133();


    }
}
