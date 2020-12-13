package homework1;

public class WalkingRouteFormatterTest {
	
	private WalkingRouteFormatter mDirections;
  	private Route mShortRoute;

  
  	public WalkingRouteFormatterTest() {
    	mDirections = new WalkingRouteFormatter();
    	mShortRoute = new Route(new GeoSegment("Ziv",
    			new GeoPoint(32783065,35014532), new GeoPoint(32781938,35016539)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Pinsker",
    			new GeoPoint(32781938,35016539), new GeoPoint(32781829,35023362)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("RavAluf Yaakov",
        			new GeoPoint(32781829,35023362), new GeoPoint(32794646,35021624)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("RavAluf Yaakov",
        			new GeoPoint(32794646,35021624), new GeoPoint(32795145,35022333)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("HaHashmal",
        			new GeoPoint(32795145,35022333), new GeoPoint(32808976,35007301)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Gdud 21 Bridge",
        			new GeoPoint(32808976,35007301), new GeoPoint(32812096,35004630)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Derech HaAtsmaut",
        			new GeoPoint(32812096,35004630), new GeoPoint(32817111,35000084)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Derech HaAtsmaut",
        			new GeoPoint(32817111,35000084), new GeoPoint(32819789,34994677)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Sderot Hamaginim",
        			new GeoPoint(32819789,34994677), new GeoPoint(32814275,34995706)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Shawarma!!!!",
        			new GeoPoint(32814275,34995706), new GeoPoint(32814275,34995706)));
  	}
  	
  	
  	public void test() {
		String directions =
			"Turn sharp right onto Ziv and walk for 5 minutes.\n" +
		    		"Turn slight left onto Pinsker and walk for 13 minutes.\n"+
		    		"Turn left onto RavAluf Yaakov and walk for 30 minutes.\n"+
		    		"Turn left onto HaHashmal and walk for 42 minutes.\n"+
		    		"Continue onto Gdud 21 Bridge and walk for 9 minutes.\n"+
		    		"Continue onto Derech HaAtsmaut and walk for 26 minutes.\n"+
		    		"Turn sharp left onto Sderot Hamaginim and walk for 12 minutes.\n"+
		    		"Turn sharp left onto Shawarma!!!! and walk for 0 minutes.\n";
		if (mDirections.computeDirections(mShortRoute, 0).equals(directions))
			System.out.println("Test passed correctly");
		else{
			System.out.println("Test not passed correctly");
			System.out.println("Your Result:");
			System.out.println(mDirections.computeDirections(mShortRoute, 0));
			System.out.println("Test (Supposed) Result:");
			System.out.println(directions);
		}
	}
  	
  	
	public static void main(String[] args) {
		WalkingRouteFormatterTest directionsTest = new WalkingRouteFormatterTest();
		directionsTest.test();
	}
}
