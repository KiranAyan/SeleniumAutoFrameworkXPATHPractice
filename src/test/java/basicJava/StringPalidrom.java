package basicJava;

public class StringPalidrom {

	public static void main(String[] args) {
		System.out.println("Find String Builder code for palidrom");
		/*String str = "codemedoc";
	       StringBuilder sb = new StringBuilder(str);
	       String reversedStr = sb.reverse().toString();
	       System.out.println(reversedStr);
	       if(str.equals(reversedStr)){
	        System.out.println("Yes palidrom");  
	}*/
		String str = "Step on no pets";
	       String [] arrayStr = str.split("");
	       StringBuilder sb = new StringBuilder();
	       String reversedStr ="";
	       for(int i=arrayStr.length-1;i>=0;i--){
	          sb = sb.append(arrayStr[i]);
	       }
	       reversedStr = sb.toString();
	       System.out.println(reversedStr.toLowerCase());
	       if(str.equalsIgnoreCase(reversedStr)){
		        System.out.println("Yes palidrom");
	       }
	       System.out.println("Very Short way palidrom");
	       /*
	       String str = "Step on no pets";
	       str = str.toLowerCase().replace(" ","");
	       System.out.println(str);
	       StringBuilder reverseBuilder = new StringBuilder(str).reverse();
	       String reverseString = reverseBuilder.toString();
	       if(reverseString.equals(str)){
	           System.out.println("Yes");
	       */
	       System.out.println("Palidrom-full proof");
	       /*String str = "Step on no pets";
       str = str.toLowerCase();
       String [] arrayStr = str.split(" ");
       String [] arrayreverseStr = new String[arrayStr.length];
       StringBuilder sbreverse = new StringBuilder();
       StringBuilder sb = new StringBuilder();
       String reversedStr ="";
       for(int i=arrayStr.length-1,j=0;i>=0;i--,j++){
          arrayreverseStr[j] = arrayStr[i];
       }
       for(int i=0;i<=arrayreverseStr.length-1;i++){
	           sbreverse= sbreverse.append(arrayreverseStr[i]);
	       }
	       reversedStr = sbreverse.toString();
	      System.out.println(reversedStr);
       for(int i=0;i<=arrayStr.length-1;i++){
	          sb = sb.append(arrayStr[i]);
	       }
	      str = sb.toString();
	      System.out.println(str);
       if(reversedStr.equals(str)){
	        System.out.println("Yes palidrom");
       }
	   
    */
}
}
