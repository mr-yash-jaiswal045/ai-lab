import java.util.Arrays;

public class MapColoring {

    // Function to check if it is safe to assign color c to vertex v
    static boolean isSafe(int v, int[][] graph, int[] color, int c) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && c == color[i]) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to solve map coloring problem using backtracking
    static boolean graphColoringUtil(int[][] graph, int m, int[] color, int v) {
        if (v == graph.length)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;
                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;
                color[v] = 0; // backtrack
            }
        }

        return false;
    }

    // Main function to solve map coloring problem
    static boolean graphColoring(int[][] graph, int m) {
        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color, 0);

        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        // Print the solution
        System.out.println("Solution exists and the coloring is:");
        for (int i = 0; i < V; i++) {
            System.out.print(color[i] + " ");
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };
        int m = 3; // Number of colors
        graphColoring(graph, m);
    }
}
