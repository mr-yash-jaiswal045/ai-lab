import java.util.*;

public class BFSvsDFS {

    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph();

        // Add nodes to the graph
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        // Add edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        // Measure time for BFS
        System.out.println("BFS:");
        long startTimeBFS = System.nanoTime();
        bfs(graph);
        long endTimeBFS = System.nanoTime();
        long elapsedTimeBFS = endTimeBFS - startTimeBFS;
        System.out.println("Time taken for BFS: " + elapsedTimeBFS + " nanoseconds");

        // Measure time for DFS
        System.out.println("DFS:");
        long startTimeDFS = System.nanoTime();
        dfs(graph);
        long endTimeDFS = System.nanoTime();
        long elapsedTimeDFS = endTimeDFS - startTimeDFS;
        System.out.println("Time taken for DFS: " + elapsedTimeDFS + " nanoseconds");
    }

    public static void bfs(Graph graph) {
        // Create a queue to store the nodes to be visited
        Queue<Node> queue = new LinkedList<>();

        // Add the root node to the queue
        queue.add(graph.getRootNode());

        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Get the next node from the queue
            Node node = queue.remove();

            // Visit the node
            System.out.println(node.getName());

            // Add the node's children to the queue
            for (Node child : node.getChildren()) {
                queue.add(child);
            }
        }
    }

    public static void dfs(Graph graph) {
        // Create a stack to store the nodes to be visited
        Stack<Node> stack = new Stack<>();

        // Add the root node to the stack
        stack.push(graph.getRootNode());

        // While the stack is not empty
        while (!stack.isEmpty()) {
            // Get the next node from the stack
            Node node = stack.pop();

            // Visit the node
            System.out.println(node.getName());

            // Add the node's children to the stack
            for (Node child : node.getChildren()) {
                stack.push(child);
            }
        }
    }
}

class Graph {

    private Node rootNode;

    public Graph() {
        this.rootNode = null;
    }

    public void addNode(String name) {
        Node node = new Node(name);

        if (this.rootNode == null) {
            this.rootNode = node;
        } else {
            this.rootNode.addChild(node);
        }
    }

    public void addEdge(String sourceName, String destinationName) {
        Node sourceNode = this.findNode(sourceName);
        Node destinationNode = this.findNode(destinationName);

        if (sourceNode != null && destinationNode != null) {
            sourceNode.addChild(destinationNode);
        }
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    private Node findNode(String name) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(this.rootNode);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (node.getName().equals(name)) {
                return node;
            }

            for (Node child : node.getChildren()) {
                queue.add(child);
            }
        }

        return null;
    }
}

class Node {

    private String name;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}
