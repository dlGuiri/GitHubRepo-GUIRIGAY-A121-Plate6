import java.util.*;

public class GuirigayProblem5 {
    private static boolean isBipartite(List<List<Integer>> adj, int V) {
        int[] colors = new int[V + 1]; // Array to store colors of vertices
        Arrays.fill(colors, -1); // Initialize colors to -1 (unvisited)

        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // Perform BFS traversal
        for (int i = 1; i <= V; i++) {
            if (colors[i] == -1) { // If vertex is unvisited
                colors[i] = 1; // Color vertex with first color (e.g., 1)
                queue.offer(i); // Add vertex to queue

                while (!queue.isEmpty()) {
                    int u = queue.poll();

                    // Check adjacent vertices
                    for (int v : adj.get(u)) {
                        if (colors[v] == -1) { // If neighbor is unvisited
                            colors[v] = 1 - colors[u]; // Assign opposite color
                            queue.offer(v); // Add neighbor to queue
                        } else if (colors[v] == colors[u]) { // If neighbor has same color
                            return false; // Graph is not bipartite
                        }
                    }
                }
            }
        }

        return true; // Graph is bipartite
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of vertices and edges
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        // Create an adjacency list to represent the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) { // Increase size by 1 to accommodate vertex labels starting from 1
            adj.add(new ArrayList<>());
        }

        // Use a set to detect duplicate edges
        Set<String> edgeSet = new HashSet<>();

        // Input the edges
        System.out.println("Enter the edges (format: vertex1 vertex2):");
        for (int i = 0; i < E; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();

            // Check for self-loops
            if (v1 == v2) {
                System.out.println("You have not provided a simple graph.");
                scanner.close();
                return;
            }

            // Check for duplicate edges
            String edge1 = v1 + "," + v2;
            String edge2 = v2 + "," + v1;
            if (edgeSet.contains(edge1) || edgeSet.contains(edge2)) {
                System.out.println("You have not provided a simple graph.");
                scanner.close();
                return;
            }

            edgeSet.add(edge1);
            edgeSet.add(edge2);

            adj.get(v1).add(v2);
            adj.get(v2).add(v1); // For undirected graphs
        }

        // Check if the graph is bipartite and output the result
        if (isBipartite(adj, V)) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }

        scanner.close();
    }
}
