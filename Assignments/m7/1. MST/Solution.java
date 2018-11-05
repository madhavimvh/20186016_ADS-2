import java.util.Scanner;
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
		int ver = Integer.parseInt(scan.nextLine());
		int edg = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph edgGraph = new EdgeWeightedGraph(ver);
		while (scan.hasNext()) {
		String[] arr = scan.nextLine().split(" ");
		Edge edge = new Edge(Integer.parseInt(arr[0]),
			Integer.parseInt(arr[1]), Double.parseDouble(arr[2]));
		edgGraph.addEdge(edge);
	}
	LazyPrimMST prim = new LazyPrimMST(edgGraph);
	System.out.printf("%.5f\n", prim.weight());

	}
}


