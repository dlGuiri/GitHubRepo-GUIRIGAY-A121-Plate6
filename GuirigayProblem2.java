import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GuirigayProblem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the size of the adjacency matrix
        System.out.print("Enter the number of vertices: ");
        int n = scanner.nextInt();
        
        // Input the adjacency matrix
        int[][] adjacencyMatrix = new int[n][n];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = scanner.nextInt();
            }
        }
        
        // Map to store the edge counts
        Map<String, Integer> edgeCounts = new HashMap<>();
        
        // List the edges and count their occurrences
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) { // Changed j from i + 1 to i to include loops
                if (adjacencyMatrix[i][j] > 0) {
                    String edge = (i + 1) + "-" + (j + 1); // Adjusted for 1-based indexing
                    edgeCounts.put(edge, edgeCounts.getOrDefault(edge, 0) + adjacencyMatrix[i][j]);
                }
            }
        }
        
        // Output the edges and their counts with numeric representation
        System.out.println("Edges and their counts (numeric):");
        for (Map.Entry<String, Integer> entry : edgeCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }

        // Output the edges and their counts with letter representation
        System.out.println("Edges and their counts (letters):");
        for (Map.Entry<String, Integer> entry : edgeCounts.entrySet()) {
            String[] vertices = entry.getKey().split("-");
            String edge = toLetter(Integer.parseInt(vertices[0]) - 1) + "-" + toLetter(Integer.parseInt(vertices[1]) - 1); // Adjusted back for 0-based indexing in toLetter method
            System.out.println(edge + ": " + entry.getValue() + " times");
        }

        scanner.close();
    }

    // Helper method to convert a vertex number to a letter
    private static String toLetter(int number) {
        return Character.toString((char) ('a' + number));
    }
}
