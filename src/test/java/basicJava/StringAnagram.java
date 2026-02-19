package basicJava;

import java.util.Arrays;

public class StringAnagram {

	public static void main(String[] args) {
		   String str1= "Listen";
	       String str2 ="Silent";
	       char [] charA1 = str1.toLowerCase().toCharArray();
	       char [] charA2 = str2.toLowerCase().toCharArray();
	       Arrays.sort(charA1);
	       Arrays.sort(charA2);
	       if(Arrays.equals(charA1,charA2)){
	           System.out.println("Analagram");
	       }
	}

}
