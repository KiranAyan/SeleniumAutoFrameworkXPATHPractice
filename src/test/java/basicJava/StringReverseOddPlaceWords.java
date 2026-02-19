package basicJava;

public class StringReverseOddPlaceWords {

	public static void main(String[] args) {
		String input = "Java is very productive and fun";
	       String[] words = input.split(" ");
	       StringBuilder finalResult = new StringBuilder();
	       for (int i = 0; i < words.length; i++) {
	            if ((i % 2 == 0)) { 
	                StringBuilder temp = new StringBuilder(words[i]);
	                words[i] = temp.reverse().toString();
	            }
	            finalResult.append(words[i]).append(" ");
	        }
	    System.out.println("Original: " + input);
	        System.out.println("Modified: " + finalResult.toString().trim());

	}

}
