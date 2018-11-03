import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
class PageRank {
	private Digraph digraph;
	private LinearProbingHashST<Integer, ArrayList<Integer>> st;
	PageRank(Digraph digraphh) {
		this.digraph = digraphh;
		st = new LinearProbingHashST<Integer, ArrayList<Integer>>();
		for (int i = 0; i < digraph.ver(); i++) {
			for (Integer k : digraph.adj(i)) {
				if (st.contains(k)) {
					ArrayList<Integer> list = st.get(k);
					list.add(i);
					st.put(k, list);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(i);
					st.put(k, list);
				}
			}
		}

		// double intial = 1.0/digraph.ver();
		// // System.out.println(intial);
		// Digraph revdigraph = digraph.reverse();
		// for (int i = 0; i < digraph.ver(); i++) {
		// 	for (int n : digraph.adj(i)) {
		// 		int count = 0;
		// 		while (count < 2) {
		// 		double index = intial/digraph.outdegree(n);
		// 		System.out.println(index);
		// 		count++;
		// 	}
		// 		// pr.put(i , index);
		// 	}
		// 	// System.out.println(pr.get(0));

		// }
	}
	public void getPr() {
		double initial = 1.0 / digraph.ver();
		System.out.println(initial);
			int i = 2;
			while (i > 0) {
		for (int n : st.keys()) {
				int outdegree = digraph.outdegree(n);
				double rank = initial / outdegree;
				i--;
				System.out.println(rank);
			}
		}
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int ver = Integer.parseInt(StdIn.readLine());
		// System.out.println(ver);
		Digraph digraph = new Digraph(ver);
		while (!StdIn.isEmpty()) {
			String[] str = StdIn.readLine().split(" ");
			// System.out.println(Arrays.toString(str));
			if (str.length > 2) {
				for (int i = 1; i < str.length; i++) {
					digraph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i]));

				}
			} else {
				digraph.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			}
		}
		System.out.println(digraph.ver() + " vertices " + digraph.E() + " edges");
		for (int j = 0; j < ver; j++) {
			System.out.print(j + ": ");
			for (int k : digraph.adj(j)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
		PageRank pagerank = new PageRank(digraph);
		pagerank.getPr();

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
