package basicJava;

import java.util.HashMap;
import java.util.Map;

public class SecondLarge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] numbers = {12, 45, 2,89, 33, 71};
	        Map<String, Integer> tracker = new HashMap<>();
	        tracker.put("max",numbers[0]);
	        tracker.put("secondMax", Integer.MIN_VALUE);
	        for(int value:numbers){
	            int currentMax = tracker.get("max");
	            int currentSecond = tracker.get("secondMax");
	            if(value>currentMax)
	            {
	                tracker.put("secondMax",currentMax);
	                tracker.put("max",value);
	            }
	            else if (value > currentSecond && value != currentMax) {
	                tracker.put("secondMax", value);
	            }
	        }
	      System.out.println(tracker.get("max"));
	      System.out.println(tracker.get("secondMax"));

	}

}
