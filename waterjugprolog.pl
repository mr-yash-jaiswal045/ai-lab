import java.util.ArrayList;
import java.util.List;
public class WaterJugProblem {
public static void main(String[] args) {
List<Jug> jugs = new ArrayList<>();
jugs.add(new Jug(4));
jugs.add(new Jug(3));
solve(jugs, 2);
}
private static void solve(List<Jug> jugs, int goal) {
Queue<State> queue = new LinkedList<>();
queue.add(new State(jugs));
while (!queue.isEmpty()) {
State state = queue.poll();
if (state.getJug(0).getWater() == goal) {
// Print the solution
System.out.println("Solution found!");
System.out.println(state);
return;
}
List<State> nextStates = generateNextStates(state);
for (State nextState : nextStates) {
queue.add(nextState);
}
}
System.out.println("No solution found!");
}
private static List<State> generateNextStates(State state) {
List<State> nextStates = new ArrayList<>();
nextStates.add(new State(state.getJug(0).pourWater(state.getJug(1))));
nextStates.add(new State(state.getJug(1).pourWater(state.getJug(0))));
nextStates.add(new State(state.getJug(0).empty()));
nextStates.add(new State(state.getJug(1).empty()));
nextStates.add(new State(state.getJug(0).fill()));
nextStates.add(new State(state.getJug(1).fill()));
return nextStates;
}
private static class Jug {
private int capacity;
private int water;
public Jug(int capacity) {
this.capacity = capacity;
this.water = 0;
}
public int getCapacity() {
return capacity;
}
public int getWater() {
return water;
}
public void setWater(int water) {
this.water = water;
}
public Jug pourWater(Jug otherJug) {
int amount = Math.min(this.water, otherJug.getCapacity() -
otherJug.getWater());
this.water -= amount;
otherJug.water += amount;
return this;
}
public Jug empty() {
this.water = 0;
return this;
}
public Jug fill() {
this.water = this.capacity;
return this;
}
public String toString() {
return "Jug{" +
"capacity=" + capacity +
", water=" + water +
'}';
}
}
private static class State {
private List<Jug> jugs;
public State(List<Jug> jugs) {
this.jugs = jugs;
}
public List<Jug> getJugs() {
return jugs;
}
public Jug getJug(int index) {
return jugs.get(index);
}
public String toString() {
return "State{" +
"jugs=" + jugs +
'}’;
}
}
}

