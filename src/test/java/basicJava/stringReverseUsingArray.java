package basicJava;

public class stringReverseUsingArray {

	public static void main(String[] args) {
		String str = "Hello World";
        char [] charArray = str.toCharArray();
        char temp;
        for(int i=0,j=str.length()-1;i<j;i++,j--){
             temp = charArray[i];
             charArray[i] = charArray[j];
             charArray[j] = temp;
            
        }
        String reversedStr = new String(charArray);
        System.out.println(reversedStr);
	}

}
