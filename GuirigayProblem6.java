import java.util.Scanner;

public class GuirigayProblem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        // Ask if the graph is directed or undirected
        System.out.print("Is the graph directed? (yes/no): ");
        boolean isDirected = scanner.next().equalsIgnoreCase("yes");

        // Initialize the adjacency matrix
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        System.out.println("Enter edges (format: vertex 1 vertex 2). Type 'done' to finish:");

        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            int source = Integer.parseInt(input);
            int destination = scanner.nextInt();

            // Adjust indices to be zero-based
            source -= 1;
            destination -= 1;

            // Increment the adjacency matrix
            adjacencyMatrix[source][destination] += 1;

            if (!isDirected && source != destination) {
                // For undirected graphs, also increment the opposite direction
                adjacencyMatrix[destination][source] += 1;
            }
        }

        scanner.close();

        // Print the adjacency matrix
        System.out.println("\nAdjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
