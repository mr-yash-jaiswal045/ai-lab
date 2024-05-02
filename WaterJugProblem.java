import java.util.*;

class WaterJugProblem {

    static class State {
        int jug1, jug2;
        List<State> path;

        State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
            this.path = new ArrayList<>();
        }

        State(int jug1, int jug2, List<State> path) {
            this.jug1 = jug1;
            this.jug2 = jug2;
            this.path = new ArrayList<>(path);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter capacity of Jug 1: ");
        int jug1Capacity = scanner.nextInt();
        System.out.print("Enter capacity of Jug 2: ");
        int jug2Capacity = scanner.nextInt();
        System.out.print("Enter target volume: ");
        int targetVolume = scanner.nextInt();

        if (targetVolume > jug1Capacity + jug2Capacity) {
            System.out.println("Warning: Target volume exceeds the sum of jug capacities. Not possible to obtain target volume.");
        } else if (!isReachable(jug1Capacity, jug2Capacity, targetVolume)) {
            System.out.println("Warning: Target volume is not reachable from the initial state. Not possible to obtain target volume.");
        } else {
            getPathIfPossible(jug1Capacity, jug2Capacity, targetVolume);
        }

        scanner.close();
    }

    private static boolean isReachable(int jug1Capacity, int jug2Capacity, int targetVolume) {
        boolean[][] visited = new boolean[jug1Capacity + 1][jug2Capacity + 1];
        Queue<State> queue = new LinkedList<>();

        State initialState = new State(0, 0);
        queue.offer(initialState);

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.jug1 == targetVolume || curr.jug2 == targetVolume) {
                return true;
            }

            visited[curr.jug1][curr.jug2] = true;

            if (curr.jug1 < jug1Capacity && !visited[jug1Capacity][curr.jug2]) {
                State nextState = new State(jug1Capacity, curr.jug2, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Fill jug1
            }
            if (curr.jug2 < jug2Capacity && !visited[curr.jug1][jug2Capacity]) {
                State nextState = new State(curr.jug1, jug2Capacity, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Fill jug2
            }
            if (curr.jug1 > 0 && !visited[0][curr.jug2]) {
                State nextState = new State(0, curr.jug2, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Empty jug1
            }
            if (curr.jug2 > 0 && !visited[curr.jug1][0]) {
                State nextState = new State(curr.jug1, 0, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Empty jug2
            }

            int pour = Math.min(curr.jug1, jug2Capacity - curr.jug2);
            if (pour > 0 && !visited[curr.jug1 - pour][curr.jug2 + pour]) {
                State nextState = new State(curr.jug1 - pour, curr.jug2 + pour, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Pour from jug1 to jug2
            }

            pour = Math.min(curr.jug2, jug1Capacity - curr.jug1);
            if (pour > 0 && !visited[curr.jug1 + pour][curr.jug2 - pour]) {
                State nextState = new State(curr.jug1 + pour, curr.jug2 - pour, curr.path);
                nextState.path.add(curr);
                queue.offer(nextState); // Pour from jug2 to jug1
            }
        }

        return false;
    }

    private static void getPathIfPossible(int jug1Capacity, int jug2Capacity, int targetVolume) {
        boolean[][] visited = new boolean[jug1Capacity + 1][jug2Capacity + 1];
        Queue<State> queue = new LinkedList<>();

        State initialState = new State(0, 0);
        initialState.path.add(initialState);
        queue.offer(initialState);

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.jug1 == targetVolume || curr.jug2 == targetVolume) {
                displayPath(curr);
                return;
            }

            visited[curr.jug1][curr.jug2] = true;

            queue.offer(new State(jug1Capacity, curr.jug2, curr.path)); // Fill jug1
            queue.offer(new State(curr.jug1, jug2Capacity, curr.path)); // Fill jug2
            queue.offer(new State(0, curr.jug2, curr.path)); // Empty jug1
            queue.offer(new State(curr.jug1, 0, curr.path)); // Empty jug2

            int pour = Math.min(curr.jug1, jug2Capacity - curr.jug2);
            queue.offer(new State(curr.jug1 - pour, curr.jug2 + pour, curr.path)); // Pour from jug1 to jug2

            pour = Math.min(curr.jug2, jug1Capacity - curr.jug1);
            queue.offer(new State(curr.jug1 + pour, curr.jug2 - pour, curr.path)); // Pour from jug2 to jug1
        }

        System.out.println("Warning: Not possible to obtain target volume.");
    }

    private static void displayPath(State state) {
        System.out.println("Steps to reach target volume:");

        List<State> path = state.path;
        for (int i = 0; i < path.size(); i++) {
            State s = path.get(i);
            System.out.println("Step " + (i + 1) + ": Jug 1: " + s.jug1 + ", Jug 2: " + s.jug2);
        }
    }
}
