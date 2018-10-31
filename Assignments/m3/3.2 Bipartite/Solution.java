import java.util.Scanner;
/**
 * Class for two color.
 */
class TwoColor {
	/**
	 * { var_description }.
	 */
	private boolean[] marked;
	/**
	 * { var_description }.
	 */
	private boolean[] color;
	/**
	 * { var_description }.
	 */
	private boolean isTwocolorable;
	/**
	 * { var_description }.
	 */
	private int[] edgeTo;
	/**
	 * { var_description }.
	 */
	private Stack<Integer> cycle;
	/**
	 * Constructs the object.
	 *
	 * @param      gr    The graphics
	 */
	TwoColor(final Graph gr) {
		isTwocolorable = true;
		marked = new boolean[gr.ver()];
		color = new boolean[gr.ver()];
		edgeTo = new int[gr.ver()];
		for (int i = 0; i < gr.ver(); i++) {
			if (!marked[i]) {
				dfs(gr, i);
			}
		}
	}
	/**
	 * { function_description }.
	 *
	 * @param      gr    The graphics
	 * @param      v     { parameter_description }
	 */
	public void dfs(final Graph gr, final int v) {
		marked[v] = true;
		for (int w : gr.adj(v)) {
			if (cycle != null) {
				return;
			}
			if (!marked[w]) {
				edgeTo[w] = v;
				color[w] = !color[v];
				dfs(gr, w);
			} else if (color[w] == color[v]) {
				isTwocolorable = false;
				cycle = new Stack<Integer>();
				cycle.push(w);
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
			}
		}
	}
	
	/**
	 * Determines if bipartite.
	 *
	 * @return     True if bipartite, False otherwise.
	 */
	public boolean isBipartite() {
		return isTwocolorable;
	}
}
public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int ver = Integer.parseInt(scan.nextLine());
		int edg = Integer.parseInt(scan.nextLine());
		Graph graph = new Graph(ver);
		while (scan.hasNext()) {
			String[] str = scan.nextLine().split(" ");
			graph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		}
		TwoColor bipar = new TwoColor(graph);
		if (bipar.isBipartite()) {
			System.out.println("Graph is bipartite");
		} else {
			System.out.println("Graph is not a bipartite");
		}


	}

}