import java.util.*;

public class GuirigayProblem1 {

    private static boolean isSimpleGraph(List<int[]> edges, int vertexCount) {
        Set<String> edgeSet = new HashSet<>();
        for (int[] edge : edges) {
            if (edge[0] == edge[1]) {
                return false; // self-loop
            }
            String edgeStr = edge[0] < edge[1] ? edge[0] + "-" + edge[1] : edge[1] + "-" + edge[0];
            if (edgeSet.contains(edgeStr)) {
                return false; // multiple edges
            }
            edgeSet.add(edgeStr);
        }
        return true;
    }

    private static void dfs(int vertex, boolean[] visited, Map<Integer, List<Integer>> adjacencyList) {
        visited[vertex] = true;
        if (adjacencyList.containsKey(vertex)) {
            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, adjacencyList);
                }
            }
        }
    }

    private static boolean isConnected(Map<Integer, List<Integer>> adjacencyList, int vertexCount) {
        boolean[] visited = new boolean[vertexCount + 1];
        dfs(1, visited, adjacencyList);
        for (int i = 1; i <= vertexCount; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private static int countConnectedComponents(Map<Integer, List<Integer>> adjacencyList, int vertexCount) {
        boolean[] visited = new boolean[vertexCount + 1];
        int components = 0;
        for (int i = 1; i <= vertexCount; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, visited, adjacencyList);
            }
        }
        return components;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertexCount = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgeCount = scanner.nextInt();

        List<int[]> edges = new ArrayList<>();
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < edgeCount; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[]{u, v});

            adjacencyList.putIfAbsent(u, new ArrayList<>());
            adjacencyList.putIfAbsent(v, new ArrayList<>());
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        boolean isSimple = isSimpleGraph(edges, vertexCount);

        if (isConnected(adjacencyList, vertexCount)) {
            System.out.println("The graph is connected.");
        } else {
            int components = countConnectedComponents(adjacencyList, vertexCount);
            System.out.println("The graph is not connected. It has " + components + " connected components.");
        }

        if (!isSimple) {
            System.out.println("Here is the answer but the graph you provided was not a simple graph.");
        }

        scanner.close();
    }
}
