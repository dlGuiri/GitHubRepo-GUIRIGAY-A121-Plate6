import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GuirigayProblem4 {
    private HashMap<Integer, ArrayList<Integer>> adjList;
    private boolean isDirected;

    public GuirigayProblem4() {
        adjList = new HashMap<>();
        isDirected = false;
    }

    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());

        adjList.get(u).add(v);

        if (u != v) {
            // For undirected graph, add both directions
            if (!adjList.get(v).contains(u)) {
                adjList.get(v).add(u);
            } else {
                // If u != v and v already contains u, this suggests a directed edge was added before
                isDirected = true;
            }
        }
    }

    public void calculateDegrees() {
        if (!isDirected) {
            System.out.println("Degrees of vertices:");
            for (int vertex : adjList.keySet()) {
                int degree = 0;
                for (int neighbor : adjList.get(vertex)) {
                    degree++;
                    // Count self-loops twice
                    if (neighbor == vertex) {
                        degree++;
                    }
                }
                System.out.println("Vertex " + vertex + " has degree: " + degree);
            }
        } else {
            System.out.println("The graph you have provided is not an undirected graph.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        GuirigayProblem4 graph = new GuirigayProblem4();

        System.out.println("Enter the edges (u v): ");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        graph.calculateDegrees();

        scanner.close();
    }
}
