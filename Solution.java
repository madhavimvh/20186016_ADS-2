import java.util.Scanner;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class Graphlist implements Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	Graphlist(int VV, int EE) {
		this.V = VV;
		this.E = EE;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		if (v != w && !hasEdge(v, w)) {
		adj[v].add(w);
		adj[w].add(v);
		}
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];

	}
	public boolean hasEdge(int v, int w) {
		for (int each : adj[v]) {
			if (each == w) {
				return true;
			}
		}
		return false;
	}
	public String display(String[] str) {
		System.out.println(V() + " vertices" + ", " + E() + " edges");
		String s = "";
		for (int i = 0; i < str.length; i++) {
			s = str[i] + ": " ;
			for (int each : adj[i]) {
				s += str[each] + "\n"; 
			}
		}
		return s;
	}
}
class Graphmatrix implements Graph {
	private int V;
	private int E;
	private int[][] adj;
	Graphmatrix(int VV, int EE) {
		this.V = VV;
		this.E = EE;
		this.adj = new int[V][V];
	}
	public int V() {
		return V;
	}
    public int E() {
    	return E;
    }
    public void addEdge(int v, int w) {
    	if (v != w && !hasEdge(v, w)) {
	    	adj[v][w] = 1;
	    	adj[w][v] = 1;
    	}

    }
    public Iterable<Integer> adj(int v) {
    	Bag<Integer> bag =  new Bag<Integer>();
    	for (int i = 0; i < V; i++) {
    		if (adj[v][i] == 1) {
    			bag.add(i);
    		}
    	}
    	return bag;
    }
    public boolean hasEdge(int v, int w) {
    	return (adj[v][w] == 1);
    }
    public String display(String[] str) {
		System.out.println(V() + " vertices" + ", " + E() + " edges");
    	String s = "";
    	for (int i = 0; i < V; i++) {
    		for (int j = 0; j < V; j++) {
    			s += adj[i][j] + " ";
    			System.out.println("string"  +" " + s);
    		}
    		s += "\n";
    	}
    	return s;

    }
}
public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		switch (str) {
			case "List":
			int v = Integer.parseInt(scan.nextLine());
			int e = Integer.parseInt(scan.nextLine());
			Graphlist list = new Graphlist(v, e);
			String[] str1 = scan.nextLine().split(",");
			while(scan.hasNext()) {
				String[] s = scan.nextLine().split(" ");
				// System.out.println(s[0]);
				list.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				
			}
			System.out.println(list.display(str1));
			case "Matrix":
			v = Integer.parseInt(scan.nextLine());
			e = Integer.parseInt(scan.nextLine());
			Graphmatrix matrix = new Graphmatrix(v, e);
			str1 = scan.nextLine().split(",");
			while(scan.hasNext()) {
				String[] s = scan.nextLine().split(" ");
				matrix.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				
			}
			System.out.println(matrix.display(str1));
		}
	}
}

