package basicJava;

public class StringAnalgram1 {

	public static void main(String[] args) {
		StringAnalgram1 obj = new StringAnalgram1();
		 System.out.println(obj.isStringAnalagram("Listen","Silent"));
	      

	}
	  public boolean isStringAnalagram (String str1,String str2){
		  str1 = str1.toLowerCase();
		  str2 = str2.toLowerCase();
			if (str1.length() != str2.length()) {
				return false;
			}
			
			int[] intArr = new int[256];
			for (int i = 0; i <= str1.length() - 1; i++) {
				intArr[str1.charAt(i)]++;
				intArr[str2.charAt(i)]--;
			}
			for (int n : intArr) {
				if (n != 0) {
					return false;
				}
      }
	  return true;
   }

}
