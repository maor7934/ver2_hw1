package homework1;

public class GeoSegmentTest {
	public static void main(String[] args) {

		Route mShortRoute;
		mShortRoute = new Route(new GeoSegment("Trumpeldor Avenue", new GeoPoint(32787000, 35020700),
				new GeoPoint(32787800, 35020700)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Hagalil", new GeoPoint(32787800, 35020700), new GeoPoint(32787800, 35021700)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Hagalil", new GeoPoint(32787800, 35021700), new GeoPoint(32785800, 35021700)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Gilboa", new GeoPoint(32785800, 35021700), new GeoPoint(32787800, 35023700)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Gilboa2", new GeoPoint(32787800, 35023700), new GeoPoint(32789800, 35025000)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Gilboa2", new GeoPoint(32789800, 35025000), new GeoPoint(32789800, 35024000)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Gilboa3", new GeoPoint(32789800, 35024000), new GeoPoint(32788800, 35024000)));
		mShortRoute = mShortRoute.addSegment(
				new GeoSegment("Gilboa4", new GeoPoint(32788800, 35024000), new GeoPoint(32799800, 35024000)));
		
		
		WalkingRouteFormatter walkDirections = new WalkingRouteFormatter();
		System.out.println(walkDirections.computeDirections(mShortRoute, 0));
		System.out.println("===============");
		DrivingRouteFormatter driveDirections = new DrivingRouteFormatter();
		System.out.println(driveDirections.computeDirections(mShortRoute, 0));
		
//		Continue onto Trumpeldor Avenue and walk for 2 minutes.
//		Turn right onto Hagalil and walk for 6 minutes.
//		Turn sharp left onto Gilboa and walk for 6 minutes.
//		Turn slight left onto Gilboa2 and walk for 7 minutes.
//		Turn left onto Gilboa3 and walk for 2 minutes.
//		U-turn onto Gilboa4 and walk for 24 minutes.
//
//		===============
//		Continue onto Trumpeldor Avenue and go 0.1 kilometers.
//		Turn right onto Hagalil and go 0.3 kilometers.
//		Turn sharp left onto Gilboa and go 0.3 kilometers.
//		Turn slight left onto Gilboa2 and go 0.3 kilometers.
//		Turn left onto Gilboa3 and go 0.1 kilometers.
//		U-turn onto Gilboa4 and go 1.2 kilometers.

	}
}