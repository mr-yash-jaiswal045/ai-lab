import java.util.*;

public class Main {

    static Map<String, List<String>> adjacencyMap = new HashMap<>();
    static Map<String, String> assignment = new HashMap<>();

    static {
        adjacencyMap.put("WA", Arrays.asList("NT", "SA"));
        adjacencyMap.put("NT", Arrays.asList("WA", "SA", "QLD"));
        adjacencyMap.put("SA", Arrays.asList("WA", "NT", "QLD", "NSW", "VIC"));
        adjacencyMap.put("QLD", Arrays.asList("NT", "SA", "NSW"));
        adjacencyMap.put("NSW", Arrays.asList("SA", "QLD", "VIC"));
        adjacencyMap.put("VIC", Arrays.asList("SA", "NSW"));
    }

    static boolean isConsistent(String region, String color, Map<String, String> assignment) {
        for (String neighbor : adjacencyMap.get(region)) {
            if (assignment.containsKey(neighbor) && assignment.get(neighbor).equals(color))
                return false;
        }
        return true;
    }

    static Optional<String> selectUnassignedVariable(Map<String, String> assignment, Set<String> colors) {
        return adjacencyMap.keySet().stream()
                .filter(region -> !assignment.containsKey(region))
                .min(Comparator.comparingInt(region -> adjacencyMap.get(region).size()));
    }

    static boolean backtracking(Map<String, String> assignment, Set<String> colors) {
        if (assignment.size() == adjacencyMap.size())
            return true;

        Optional<String> unassignedVar = selectUnassignedVariable(assignment, colors);
        if (!unassignedVar.isPresent())
            return false;

        String region = unassignedVar.get();
        for (String color : colors) {
            if (isConsistent(region, color, assignment)) {
                assignment.put(region, color);
                if (backtracking(assignment, colors))
                    return true;
                assignment.remove(region);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Set<String> colors = new HashSet<>(Arrays.asList("Red", "Green", "Blue"));

        if (backtracking(assignment, colors)) {
            System.out.println("Solution exists:");
            assignment.forEach((region, color) -> System.out.println(region + " -> " + color));
        } else {
            System.out.println("Solution does not exist");
        }
    }
}
