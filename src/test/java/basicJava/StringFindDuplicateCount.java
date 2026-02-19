package basicJava;

public class StringFindDuplicateCount {

	public static void main(String[] args) {
	       String str = "ListenSilent";
	       str = str.toLowerCase();
	       int [] intArr= new int[256];
	       for(int i=0;i<=str.length()-1;i++){
	           intArr[str.charAt(i)]++;
	       }
	       for(int i = 0;i<=intArr.length-1;i++){
	           if(intArr[i]!=0){
	               char ch = (char)i;
	               System.out.println(ch +"  :"+intArr[i] );
	           }
	          
	}
   }
}
