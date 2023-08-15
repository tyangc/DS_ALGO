package twopointers.samedirection;

/*
 * https://chowdera.com/2022/03/202203240655072537.html
 * SearchWord
Give me a searchWord And a resultWord, Ask at least searchWord Add a few characters at the end , It can make resultWord Be a part of it subsequence. 
Take a chestnut ：searchWord=“armaze”,resultWord=”amazon”, You should go back to 2（ add to on）.

Ideas

It's two pointers p1,p2 Traverse two strings respectively , If the characters pointed to are the same , Move the double pointer back one bit at the same time , 
On the contrary, only move to searchWord The pointer to , Until any pointer reaches the end . return resultString The length of resultString Difference of pointer position
 */
public class SearchWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(addCharacter("aarmaze", "amazon"));
	}

	public static int addCharacter(String searchWord, String resultWord) {
		if (resultWord == null || resultWord.length() == 0) return searchWord.length();
		
		int searchIndex = 0, resultIndex = 0;
		
		while(searchIndex<searchWord.length() && resultIndex<resultWord.length()) {
			if (searchWord.charAt(searchIndex) == resultWord.charAt(resultIndex)) {
				resultIndex++;
			}
			searchIndex++;
		}
		
		return resultWord.length()-resultIndex;
	}
}
