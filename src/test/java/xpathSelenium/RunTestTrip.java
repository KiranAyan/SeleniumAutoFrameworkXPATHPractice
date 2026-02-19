package xpathSelenium;

import org.testng.Assert;

public class RunTestTrip {
	public static void main(String[] args) {
		TripDotCom TDC = new TripDotCom();
		TDC.launchBrowser();
		int day = 5; String month = "April"; String year = "2026";
		TDC.selectFullDate(day, month, year);
		TDC.selectFullDate(4,"May", "2026");
		System.out.println("Running Trip.com Test...");
		System.out.println("Conflict here");
		
		/*TDC.selectCity();
		try{Assert.assertTrue(TDC.verifyFlightsAreSortedByCheapest(),"FAIL: The flights are not sorted by cheapest price first!");}
		catch(AssertionError e) {
			System.out.println(e.getMessage());
		}*/
		TDC.tearDown();		
	}

}
