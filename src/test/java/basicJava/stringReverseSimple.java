package basicJava;

public class stringReverseSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Hello World";
        String reversedStr = "";
        for(int i=str.length()-1;i>=0;i--){
        reversedStr=reversedStr+str.charAt(i);
        }
        System.out.println(reversedStr);
        /*
         * 
String str = "Hello World";
        String reversedStr = "";
        StringBuilder sb = new StringBuilder(str);
        for(int i=str.length()-1;i>=0;i--){
        sb=sb.append(str.charAt(i));
        }
        reversedStr = sb.toString();
        System.out.println(reversedStr);

	}
	
*/      
        System.out.println("Try Using StringBuilder funtion reverse()");
/*public static void main(String[] args) {
        String str = "Hello World";
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();
        System.out.println(reversed);*/
	}
	

}
