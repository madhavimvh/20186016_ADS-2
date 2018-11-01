public class Solution {
	public static void main(String[] args) {
		String synsets = StdIn.readString();
		String hypernyms = StdIn.readString();
		// wordnet.readfile(synsets);
		String type = StdIn.readString();
		if (type.equals("Graph")) {
			
		WordNet wordnet = new WordNet(synsets, hypernyms);
		//wordnet.readhyn()
		}

	}
}
