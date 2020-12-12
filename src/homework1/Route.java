package homework1;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * A Route is a path that traverses arbitrary GeoSegments, regardless
 * of their names.
 * <p>
 * Routes are immutable. New Routes can be constructed by adding a segment 
 * to the end of a Route. An added segment must be properly oriented; that 
 * is, its p1 field must correspond to the end of the original Route, and
 * its p2 field corresponds to the end of the new Route.
 * <p>
 * Because a Route is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily
 * the same as the distance along a straight line between its endpoints.
 * <p>
 * Lastly, a Route may be viewed as a sequence of geographical features,
 * using the <tt>getGeoFeatures()</tt> method which returns an Iterator of
 * GeoFeature objects.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint            // location of the start of the route
 *   end : GeoPoint              // location of the end of the route
 *   startHeading : angle        // direction of travel at the start of the route, in degrees
 *   endHeading : angle          // direction of travel at the end of the route, in degrees
 *   geoFeatures : sequence      // a sequence of geographic features that make up this Route
 *   geoSegments : sequence      // a sequence of segments that make up this Route
 *   length : real               // total length of the route, in kilometers
 *   endingGeoSegment : GeoSegment  // last GeoSegment of the route
 * </pre>
 **/
public class Route {

	
 	// TODO Write abstraction function and representation invariant

	
	final GeoPoint start,end;
	final double startHeading , endHeading;
	final ArrayList<GeoSegment> geoSegments;
	final ArrayList<GeoFeature> geoFeatures;
	final double length;

	// representation invariant:
		// object is not null
		// 0 <= startHeading,endHeading < 360 
		// 0 < length
		// 
	public static final double MIN_HEADING = 0;
	public static final double MAX_HEADING = 360;
		// abstraction function:
		// represent a route: a collection of geographical segments that with its features.
	private void checkRep() {
		assert (this != null) && (this instanceof Route) && (this.length > 0) &&
			(0 <= this.startHeading) && (0 <= this.endHeading) &&
			(this.startHeading < 360) && (this.endHeading < 360) &&
			(this.geoFeatures.size() > 0) && (this.geoSegments.size() > 0) &&
			(this.length > 0 );

	}

  	/**
  	 * Constructs a new Route.
     * @requires gs != null
     * @effects Constructs a new Route, r, such that
     *	        r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public Route(GeoSegment gs) {
  		// TODO Implement this constructor
  		this.startHeading = gs.getHeading();
  		this.endHeading = gs.getHeading();
  		this.start = gs.getP1();
  		this.end = gs.getP2();
		this.geoSegments = new ArrayList<GeoSegment>();
		this.geoSegments.add(gs);
		this.geoFeatures = new ArrayList<GeoFeature>();
		GeoFeature gf =new GeoFeature(gs);
		this.geoFeatures.add(gf);
		
		this.length = gf.getLength();
		
		this.checkRep();
  	}
  	
  	/**
     * Constructs a new route that is equal to old route, with gs appended to
     * its end.
   	 * @requires gs != null && gs.p1 == rOld.end
     * @return a new Route r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *         r.length = this.length + gs.length
     **/
  	public Route(Route rOld, GeoSegment gs) {
  		rOld.checkRep();
  		
  		this.startHeading = rOld.getStartHeading();
  		this.endHeading = gs.getHeading();
  		this.start = rOld.getStart();
  		this.end = gs.getP2();
  		this.geoSegments = new ArrayList<GeoSegment>(rOld.geoSegments);
  		this.geoSegments.add(gs);
  		this.geoFeatures = new ArrayList<GeoFeature>(rOld.geoFeatures);
  		this.geoFeatures.add(new GeoFeature(gs));
  		
  		this.length = rOld.getLength() + gs.getLength();
  		
  		this.checkRep();

  	}


    /**
     * Returns location of the start of the route.
     * @return location of the start of the route.
     **/
  	public GeoPoint getStart() {
  		// TODO Implement this method
  		return this.start;
  	}


  	/**
  	 * Returns location of the end of the route.
     * @return location of the end of the route.
     **/
  	public GeoPoint getEnd() {
  		// TODO Implement this method
  		return this.end;
  	}


  	/**
  	 * Returns direction of travel at the start of the route, in degrees.
   	 * @return direction (in compass heading) of travel at the start of the
   	 *         route, in degrees.
   	 **/
  	public double getStartHeading() {
  		// TODO Implement this method
  		return this.startHeading;
  	}


  	/**
  	 * Returns direction of travel at the end of the route, in degrees.
     * @return direction (in compass heading) of travel at the end of the
     *         route, in degrees.
     **/
  	public double getEndHeading() {
  		// TODO Implement this method
  		return this.endHeading;
  	}


  	/**
  	 * Returns total length of the route.
     * @return total length of the route, in kilometers.  NOTE: this is NOT
     *         as-the-crow-flies, but rather the total distance required to
     *         traverse the route. These values are not necessarily equal.
   	 **/
  	public double getLength() {
  		// TODO Implement this method
  		return this.length;
  	}


  	/**
     * Creates a new route that is equal to this route with gs appended to
     * its end.
   	 * @requires gs != null && gs.p1 == this.end
     * @return a new Route r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *         r.length = this.length + gs.length
     **/
  	public Route addSegment(GeoSegment gs) {
  		// TODO Implement this method
  		this.checkRep();
  		return new Route(this, gs);
  	}


    /**
     * Returns an Iterator of GeoFeature objects. The concatenation
     * of the GeoFeatures, in order, is equivalent to this route. No two
     * consecutive GeoFeature objects have the same name.
     * @return an Iterator of GeoFeatures such that
     * <pre>
     *      this.start        = a[0].start &&
     *      this.startHeading = a[0].startHeading &&
     *      this.end          = a[a.length - 1].end &&
     *      this.endHeading   = a[a.length - 1].endHeading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length - 1 => (a[i].name != a[i+1].name &&
     *                                     a[i].end  == a[i+1].start))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoFeature
     **/
  	public Iterator<GeoFeature> getGeoFeatures() {
  		// TODO Implement this method
  		this.checkRep();
  		Iterator<GeoFeature> i_gf = this.geoFeatures.iterator();
  		return i_gf;
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this route.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum (0 <= i < a.length) . a[i].length
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     **/
  	public Iterator<GeoSegment> getGeoSegments() {
  		// TODO Implement this method
  		this.checkRep();
  		Iterator<GeoSegment> i_gs = this.geoSegments.iterator();
  		return i_gs;
  	}


  	/**
     * Compares the specified Object with this Route for equality.
     * @return true iff (o instanceof Route) &&
     *         (o.geoFeatures and this.geoFeatures contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
  		// TODO Implement this method
  		Route otherRoute;
  		if ((this !=null) && (o != null) && (o instanceof Route)){
  			otherRoute = (Route) o;
  	  		Iterator<GeoFeature> i_gf = this.getGeoFeatures();
  	  		Iterator<GeoFeature> j_gf = otherRoute.getGeoFeatures();
  	  		while (i_gf.hasNext() && j_gf.hasNext()) {
  	  			if (!i_gf.next().equals(j_gf.next())) {
  	  				return false;
  	  			}
  	  		}
  	  		if (i_gf.hasNext() || j_gf.hasNext()) {
  	  			return false;
  	  		}
  	  		return true;
  		}
  		return false;  		
  	}


    /**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
    	// This implementation will work, but you may want to modify it
    	// for improved performance.
  		int hashValue = 1;
  		this.checkRep();
  		try {
  			Iterator<GeoFeature> i_gf = this.getGeoFeatures();
  	  		while (i_gf.hasNext()) {
  	  			hashValue = hashValue*i_gf.hashCode();
  	  		}		
  		} catch (Exception e) {
  			hashValue = 1;
  		}
  		return hashValue;
  	}


    /**
     * Returns a string representation of this.
     * @return a string representation of this.
     **/
  	public String toString() {
  		String outString = "The Route is: \n";
  		Iterator<GeoSegment> i_gs = this.getGeoSegments();
  		while (i_gs.hasNext()) {
  			outString = outString + i_gs.next().toString();
  		}
  		return outString;
  	}

}
