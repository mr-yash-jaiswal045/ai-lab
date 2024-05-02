import org.jpl7.*;

public class PrologBFSDFS {

    public static void loadPrologFile(String filename) {
        Query q1 = new Query("consult", new Term[] {new Atom(filename)});
        System.out.println((q1.hasSolution() ? "succeeded" : "failed") + " to load the file.");
    }

    public static void bfs() {
        Query q2 = new Query("bfs", new Term[] {});
        while (q2.hasMoreSolutions()) {
            System.out.println(q2.nextSolution());
        }
    }

    public static void dfs() {
        Query q3 = new Query("dfs", new Term[] {});
        while (q3.hasMoreSolutions()) {
            System.out.println(q3.nextSolution());
        }
    }

    public static void nQueens(int n) {
        Query q4 = new Query("n_queens(" + n + ")");
        while (q4.hasMoreSolutions()) {
            System.out.println(q4.nextSolution());
        }
    }

    public static void main(String[] args) {
        // Load Prolog file
        loadPrologFile("graph_traversal.pl");

        // Execute BFS
        System.out.println("BFS:");
        bfs();

        // Execute DFS
        System.out.println("DFS:");
        dfs();

        // Solve N-Queens problem with board size 8
        System.out.println("N-Queens:");
        nQueens(8);
    }
}
