public class Solution {
	public static void main(String[] args) {
		String synsets = StdIn.readString();
		String hypernyms = StdIn.readString();
		// wordnet.readfile(synsets);
		String type = StdIn.readString();
		try {
		if (type.equals("Graph")) {
		WordNet wordnet = new WordNet(synsets, hypernyms);
		//wordnet.readhyn()
		} 
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
		// else if (type.equals("Queries"))
	}

	}
}
