import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        String[] ints = scan.nextLine().split(" ");
        int ver = Integer.parseInt(ints[0]);
        int edg = Integer.parseInt(ints[1]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(ver);
        String[] arr = scan.nextLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            hashmap.put(arr[i], i);
        }
        for (int i = 0; i < edg; i++) {
            String[] edges = scan.nextLine().split(" ");
            graph.addEdge(new Edge(hashmap.get(edges[0]),
                hashmap.get(edges[1]),
                Integer.parseInt(edges[2])));
        }
        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            String[] str = scan.nextLine().split(" ");
            int a = hashmap.get(str[0]);
            DijkstraUndirectedSP sp
            = new DijkstraUndirectedSP(graph, a);
            if (sp.hasPathTo(hashmap.get(str[1]))) {
                System.out.println(
                    (int) sp.distTo(hashmap.get(str[1])));
            }
        }
    }
}
