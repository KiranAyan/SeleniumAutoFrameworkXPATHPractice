package basicJava;

public class StringToInteger {

	public static void main(String[] args) {
		String str = "xyz1234%$";
	    String digits = str.replaceAll("[^0-9]","");
	    Integer number;
	    number = Integer.parseInt(digits);
	    System.out.println(number);

	}

}
