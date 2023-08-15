package amazon;

import java.util.Arrays;

public class Regex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Splitting strings through regular expressions by punctuation and whitespace etc in java
		
		String Text = "But I know. For example, the word \"can\'t\" should";
		
		String[] res = Text.split("[\\p{Punct}\\s]+");
		String[] res1 = Text.replaceAll("[^ a-zA-Z]", "").split(" ");
		
		System.out.println(Arrays.toString(res));
		System.out.println(Arrays.toString(res1));


	}

}
