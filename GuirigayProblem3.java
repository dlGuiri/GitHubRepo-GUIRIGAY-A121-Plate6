import java.util.ArrayList;
import java.util.Scanner;

public class GuirigayProblem3 {
    private ArrayList<ArrayList<Integer>> adjList;
    private boolean[] visited;

    public GuirigayProblem3(int vertices) {
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertices];
    }

    public void addEdge(int u, int v) {
        adjList.get(u - 1).add(v - 1);
        adjList.get(v - 1).add(u - 1);
    }

    private boolean isCyclicS(int v, int parent) {
        visited[v] = true;

        for (int i : adjList.get(v)) {
            if (!visited[i]) {
                if (isCyclicS(i, v)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean isCyclic() {
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                if (isCyclicS(i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        GuirigayProblem3 graph = new GuirigayProblem3(vertices);

        System.out.println("Enter the edges (u v): ");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        if (graph.isCyclic()) {
            System.out.println("The graph contains a cycle.");
        } else if (vertices < 3) {
            System.out.println("There are only two vertices. The graph cannot be a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }

        scanner.close();
    }
}
