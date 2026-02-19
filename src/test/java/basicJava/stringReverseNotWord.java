package basicJava;

public class stringReverseNotWord {

	public static void main(String[] args) {
		 String str = "Hello World";
	        String [] arrayStr = str.split(" ");
	        String reversedStr ="";
	        StringBuilder sb = new StringBuilder();
	        for(int i=arrayStr.length-1;i>=0;i--){
	            if(i==0){
	                sb=sb.append(arrayStr[i]);
	                break;
	            }
	        sb=sb.append(arrayStr[i]+" ");
	        }
	        reversedStr = sb.toString();
	        System.out.println(reversedStr);
	        
	    }

	}
