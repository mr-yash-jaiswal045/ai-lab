import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EightQueenCompare {
    static int bruteForceSteps = 0;
    static int geneticSteps = 0;

    public static void main(String[] args) {
        int n = 8;

        // Brute Force approach
        bruteForce(n);
        System.out.println("Brute Force Steps: " + bruteForceSteps);

        // Genetic algorithm approach
        genetic(n);
        System.out.println("Genetic Algorithm Steps: " + geneticSteps);
    }

    // Brute Force approach
    public static void bruteForce(int n) {
        int[] queens = new int[n];
        solve(queens, 0, n);
    }

    public static boolean solve(int[] queens, int row, int n) {
        if (row == n) {
            printSolution(queens);
            return true;
        }
        for (int col = 0; col < n; col++) {
            queens[row] = col;
            bruteForceSteps++;
            if (isValid(queens, row) && solve(queens, row + 1, n)) {
                return true;
            }
        }
        return false;
    }

    // Genetic algorithm approach
    public static void genetic(int n) {
        int populationSize = 100;
        int maxGenerations = 1000;
        List<int[]> population = initializePopulation(n, populationSize);
        Random random = new Random();

        for (int generation = 1; generation <= maxGenerations; generation++) {
            List<int[]> nextGeneration = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                int[] parent1 = selectParent(population, random);
                int[] parent2 = selectParent(population, random);
                int[] offspring = crossover(parent1, parent2, random);
                mutate(offspring, random);
                if (isSolution(offspring)) {
                    printSolution(offspring);
                    geneticSteps++;
                    return;
                }
                nextGeneration.add(offspring);
                geneticSteps++;
            }
            population = nextGeneration;
        }
    }

    public static List<int[]> initializePopulation(int n, int populationSize) {
        List<int[]> population = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            int[] chromosome = new int[n];
            for (int j = 0; j < n; j++) {
                chromosome[j] = random.nextInt(n);
            }
            population.add(chromosome);
        }
        return population;
    }

    public static int[] selectParent(List<int[]> population, Random random) {
        int index = random.nextInt(population.size());
        return population.get(index);
    }

    public static int[] crossover(int[] parent1, int[] parent2, Random random) {
        int[] offspring = new int[parent1.length];
        int crossoverPoint = random.nextInt(parent1.length);
        System.arraycopy(parent1, 0, offspring, 0, crossoverPoint);
        System.arraycopy(parent2, crossoverPoint, offspring, crossoverPoint, parent2.length - crossoverPoint);
        return offspring;
    }

    public static void mutate(int[] chromosome, Random random) {
        if (random.nextDouble() < 0.05) { // Mutation probability
            int index = random.nextInt(chromosome.length);
            chromosome[index] = random.nextInt(chromosome.length);
        }
    }

    public static boolean isSolution(int[] queens) {
        for (int i = 0; i < queens.length; i++) {
            for (int j = i + 1; j < queens.length; j++) {
                if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[] queens, int row) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == queens[row] || Math.abs(queens[i] - queens[row]) == row - i) {
                return false;
            }
        }
        return true;
    }

    public static void printSolution(int[] queens) {
        System.out.print("Final State: ");
        for (int i = 0; i < queens.length; i++) {
            System.out.print("(" + i + "," + queens[i] + ") ");
        }
        System.out.println();
    }
}
