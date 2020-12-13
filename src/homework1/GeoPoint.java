package homework1;

/**
 * A GeoPoint is a point on the earth. GeoPoints are immutable.
 * <p>
 * North latitudes and east longitudes are represented by positive numbers.
 * South latitudes and west longitudes are represented by negative numbers.
 * <p>
 * The code may assume that the represented points are nearby the Technion.
 * <p>
 * <b>Implementation direction</b>:<br>
 * The Ziv square is at approximately 32 deg. 46 min. 59 sec. N
 * latitude and 35 deg. 0 min. 52 sec. E longitude. There are 60 minutes
 * per degree, and 60 seconds per minute. So, in decimal, these correspond
 * to 32.783098 North latitude and 35.014528 East longitude. The 
 * constructor takes integers in millionths of degrees. To create a new
 * GeoPoint located in the the Ziv square, use:
 * <tt>GeoPoint zivCrossroad = new GeoPoint(32783098,35014528);</tt>
 * <p>
 * Near the Technion, there are approximately 110.901 kilometers per degree
 * of latitude and 93.681 kilometers per degree of longitude. An
 * implementation may use these values when determining distances and
 * headings.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   latitude :  real        // latitude measured in degrees
 *   longitude : real        // longitude measured in degrees
 * </pre>
 **/
public class GeoPoint {

	/** Minimum value the latitude field can have in this class. **/
	public static final int MIN_LATITUDE  =  -90 * 1000000;
	    
	/** Maximum value the latitude field can have in this class. **/
	public static final int MAX_LATITUDE  =   90 * 1000000;
	    
	/** Minimum value the longitude field can have in this class. **/
	public static final int MIN_LONGITUDE = -180 * 1000000;
	    
	/** Maximum value the longitude field can have in this class. **/
	public static final int MAX_LONGITUDE =  180 * 1000000;

  	/**
   	 * Approximation used to determine distances and headings using a
     * "flat earth" simplification.
     */
  	public static final double KM_PER_DEGREE_LATITUDE = 110.901;

  	/**
     * Approximation used to determine distances and headings using a
     * "flat earth" simplification.
     */
  	public static final double KM_PER_DEGREE_LONGITUDE = 93.681;
  	
  	/**
  	 * integer used too normalize calcualtions
  	 */
  	public static final int ONE_MILLION = 1000000;
	// Implementation hint:
	// Doubles and floating point math can cause some problems. The exact
	// value of a double can not be guaranteed except within some epsilon.
	// Because of this, using doubles for the equals() and hashCode()
	// methods can have erroneous results. Do not use floats or doubles for
	// any computations in hashCode(), equals(), or where any other time 
	// exact values are required. (Exact values are not required for length 
	// and distance computations). Because of this, you should consider 
	// using ints for your internal representation of GeoPoint. 

  	
  	final int latitude_int;
  	final int longtitude_int;
  	
  	/* Checks the rep. Inv.
  	 * @throws Assertion error if violated. 
  	 */
  	private void checkRep () {
  		assert 
  		this.latitude_int <= MAX_LATITUDE && this.latitude_int >=MIN_LATITUDE
  				&& this.longtitude_int <= MAX_LATITUDE && this.longtitude_int >=MIN_LONGITUDE:
  					"Rep. Inv. of class GeoPoint is violated.";
  	}
  	
  	
  	/**
  	 * Constructs GeoPoint from a latitude and longitude.
     * @requires the point given by (latitude, longitude) in millionths
   	 *           of a degree is valid such that:
   	 *           (MIN_LATITUDE <= latitude <= MAX_LATITUDE) and
     * 	 		 (MIN_LONGITUDE <= longitude <= MAX_LONGITUDE)
   	 * @effects constructs a GeoPoint from a latitude and longitude
     *          given in millionths of degrees.
   	 **/
  	public GeoPoint(int latitude, int longitude) {
  		this.latitude_int = latitude;
  		this.longtitude_int = longitude;
  		this.checkRep();
  		
  	}

  	 
  	/**
     * Returns the latitude of this.
     * @return the latitude of this in millionths of degrees.
     */
  	public int getLatitude() {
  		this.checkRep();
  		return this.latitude_int;
  	}


  	/**
     * Returns the longitude of this.
     * @return the latitude of this in millionths of degrees.
     */
  	public int getLongitude() {
  		this.checkRep();
  		return this.longtitude_int;
  	}


  	/**
     * Computes the distance between GeoPoints.
     * @requires gp != null
     * @return the distance from this to gp, using the flat-surface, near
     *         the Technion approximation.
     **/
  	public double distanceTo(GeoPoint gp) {
  		this.checkRep();
  		gp.checkRep();
  		try {
  			// Exact values not required, can use doubles
  			double y_distance = ((this.latitude_int - gp.latitude_int)/ONE_MILLION)*KM_PER_DEGREE_LATITUDE;
  			double x_distance = ((this.longtitude_int - gp.longtitude_int)/ONE_MILLION)*KM_PER_DEGREE_LONGITUDE;
  			double total_distance = Math.sqrt(Math.pow(x_distance, 2)  + Math.pow(y_distance, 2));
  			return total_distance;
  			
  		}
  		 finally {
  			 gp.checkRep();
  			 this.checkRep();
  		 }
  	}

  	/**
     * Computes the compass heading between GeoPoints.
     * @requires gp != null && !this.equals(gp)
     * @return the compass heading h from this to gp, in degrees, using the
     *         flat-surface, near the Technion approximation, such that
     *         0 <= h < 360. In compass headings, north = 0, east = 90,
     *         south = 180, and west = 270.
     **/
  	public double headingTo(GeoPoint gp) {
		// Implementation hints:
		// 1. You may find the mehtod Math.atan2() useful when
		// implementing this method. More info can be found at:
		// http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
		//
		// 2. Keep in mind that in our coordinate system, north is 0
		// degrees and degrees increase in the clockwise direction. By
		// mathematical convention, "east" is 0 degrees, and degrees
		// increase in the counterclockwise direction.

		// TODO Implement this method
		this.checkRep();
		gp.checkRep();
		assert !this.equals(gp) : "headingTo got 2 identical points";
		assert gp != null : "headingTo got null gp";
		try {
			double relative_y = (this.latitude_int - gp.latitude_int) / ONE_MILLION * KM_PER_DEGREE_LATITUDE;
			double relative_x = (this.longtitude_int - gp.longtitude_int) / ONE_MILLION * KM_PER_DEGREE_LATITUDE;
			double radian = - Math.atan2(relative_y, relative_x);
			double degree = Math.toDegrees(radian) + 90; // TODO: move to const
			return degree;
		} finally {
			this.checkRep();
			gp.checkRep();
		}
	}

	/**
	 * Compares the specified Object with this GeoPoint for equality.
	 * 
	 * @return gp != null && (gp instanceof GeoPoint) && gp.latitude = this.latitude
	 *         && gp.longitude = this.longitude
	 **/
	public boolean equals(Object gp) {
		this.checkRep();
		try {
			return gp != null && gp instanceof GeoPoint && ((GeoPoint) gp).latitude_int == this.latitude_int
					&& ((GeoPoint) gp).longtitude_int == this.longtitude_int;
		} finally {
			this.checkRep();
		}

	}

	/**
	 * Returns a hash code value for this GeoPoint.
	 * 
	 * @return a hash code value for this GeoPoint.
	 **/
	public int hashCode() {
		this.checkRep();
		return this.latitude_int * 2 + this.longtitude_int;
	}

	/**
	 * Returns a string representation of this GeoPoint.
	 * 
	 * @return a string representation of this GeoPoint.
	 **/
	public String toString() {
		this.checkRep();
		return String.format("(latitude,longitude) = (%d,%d)",this.latitude_int,this.longtitude_int);
	}
}