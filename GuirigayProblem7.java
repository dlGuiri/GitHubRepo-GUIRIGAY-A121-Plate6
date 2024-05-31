import java.util.*;

public class GuirigayProblem7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        int[][] incidenceMatrix = new int[numVertices][numEdges];

        int u = -1, v = -1;
        boolean skipNext = false;

        for (int i = 0; i < numEdges; i++) {
            if (skipNext) {
                System.out.println("Reusing previous edge for iteration " + (i + 1) + ": (" + u + ", " + v + ")");
            } else {
                System.out.print("\nEnter the vertices for edge " + (i + 1) + " (two vertices separated by space): ");
                u = scanner.nextInt();
                v = scanner.nextInt();
            }

            int count;
            if (skipNext) {
                count = 1;
                skipNext = false;
            } else {
                System.out.print("Enter the number of times this edge appears: ");
                count = scanner.nextInt();
                if (count == 2) {
                    skipNext = true;
                }
            }

            // Set the matrix values to 1 regardless of the actual count
            if (u == v) {
                // Loop case: Only increment once
                incidenceMatrix[u - 1][i] = 1;
            } else {
                incidenceMatrix[u - 1][i] = 1;
                incidenceMatrix[v - 1][i] = 1;
            }
        }

        System.out.println("\nIncidence Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numEdges; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
