import java.util.*;

public class GuirigayProblem8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of vertices and edges for the first graph
        System.out.print("Enter the number of vertices for the first graph: ");
        int n1 = scanner.nextInt();
        System.out.print("Enter the number of edges for the first graph: ");
        int e1 = scanner.nextInt();

        // Read the edges for the first graph
        int[][] graph1 = new int[n1 + 1][n1 + 1];
        System.out.println("\nEnter the edges for the first graph:");
        for (int i = 0; i < e1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph1[u][v] = 1;
            graph1[v][u] = 1; // Assuming the graph is undirected
        }

        // Read the number of vertices and edges for the second graph
        System.out.print("\nEnter the number of vertices for the second graph: ");
        int n2 = scanner.nextInt();
        System.out.print("Enter the number of edges for the second graph: ");
        int e2 = scanner.nextInt();

        // Read the edges for the second graph
        int[][] graph2 = new int[n2 + 1][n2 + 1];
        System.out.println("Enter the edges for the second graph:");
        for (int i = 0; i < e2; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph2[u][v] = 1;
            graph2[v][u] = 1; // Assuming the graph is undirected
        }

        // Check if the graphs are isomorphic
        boolean isomorphic = areIsomorphic(graph1, graph2, n1, n2);
        if (isomorphic) {
            System.out.println("The graphs are isomorphic.");
        } else {
            System.out.println("The graphs are not isomorphic.");
        }
        scanner.close();
    }

    public static boolean areIsomorphic(int[][] graph1, int[][] graph2, int n1, int n2) {
        if (n1 != n2) {
            return false;
        }
        return vf2(graph1, graph2, n1);
    }

    private static boolean vf2(int[][] graph1, int[][] graph2, int n) {
        int[] map1 = new int[n + 1];
        int[] map2 = new int[n + 1];
        Arrays.fill(map1, -1);
        Arrays.fill(map2, -1);
        return isIsomorphicMap(graph1, graph2, map1, map2, n, 1);
    }

    private static boolean isIsomorphicMap(int[][] graph1, int[][] graph2, int[] map1, int[] map2, int n, int node) {
        if (node > n) {
            return true;
        }

        for (int i = 1; i <= n; i++) {
            if (map2[i] == -1 && isValid(graph1, graph2, map1, map2, node, i, n)) {
                map1[node] = i;
                map2[i] = node;

                if (isIsomorphicMap(graph1, graph2, map1, map2, n, node + 1)) {
                    return true;
                }

                map1[node] = -1;
                map2[i] = -1;
            }
        }
        return false;
    }

    private static boolean isValid(int[][] graph1, int[][] graph2, int[] map1, int[] map2, int node1, int node2, int n) {
        for (int i = 1; i <= n; i++) {
            if (map1[i] != -1) {
                if (graph1[node1][i] != graph2[node2][map1[i]]) {
                    return false;
                }
            }
        }
        return true;
    }
}
