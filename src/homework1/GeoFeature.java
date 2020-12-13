package homework1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A GeoFeature represents a route from one location to another along a single
 * geographic feature. GeoFeatures are immutable.
 * <p>
 * GeoFeature abstracts over a sequence of GeoSegments, all of which have the
 * same name, thus providing a representation for nonlinear or nonatomic
 * geographic features. As an example, a GeoFeature might represent the course
 * of a winding river, or travel along a road through intersections but
 * remaining on the same road.
 * <p>
 * GeoFeatures are immutable. New GeoFeatures can be constructed by adding a
 * segment to the end of a GeoFeature. An added segment must be properly
 * oriented; that is, its p1 field must correspond to the end of the original
 * GeoFeature, and its p2 field corresponds to the end of the new GeoFeature,
 * and the name of the GeoSegment being added must match the name of the
 * existing GeoFeature.
 * <p>
 * Because a GeoFeature is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily the
 * same as the distance along a straight line between its endpoints.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * 
 * <pre>
 *   start : GeoPoint       // location of the start of the geographic feature
 *   end : GeoPoint         // location of the end of the geographic feature
 *   startHeading : angle   // direction of travel at the start of the geographic feature, in degrees
 *   endHeading : angle     // direction of travel at the end of the geographic feature, in degrees
 *   geoSegments : sequence	// a sequence of segments that make up this geographic feature
 *   name : String          // name of geographic feature
 *   length : real          // total length of the geographic feature, in kilometers
 * </pre>
 **/
public class GeoFeature {

	// Implementation hint:
	// When asked to return an Iterator, consider using the iterator() method
	// in the List interface. Two nice classes that implement the List
	// interface are ArrayList and LinkedList. If comparing two Lists for
	// equality is needed, consider using the equals() method of List. More
	// info can be found at:
	// http://docs.oracle.com/javase/8/docs/api/java/util/List.html

	final GeoPoint start;
	final GeoPoint end;
	final double length;
	final double startHeading;
	final double endHeading;
	final ArrayList<GeoSegment> geoSegments;
	final String name;

	// TODO Write abstraction function and representation invariant
	private void checkRep() {
    	assert this.geoSegments.size() != 0 : "GeoFeature RI violated: no segments";
    	assert this.name != null : "GeoFeature RI vioalted: name is null";
    	assert !this.start.equals(this.end) : "GeoFeature RI violated - start equals end";
    	GeoSegment first = null, last = null;
    	for (Iterator<GeoSegment> iter = this.geoSegments.iterator(); iter.hasNext();) {
    		last = first;
    		first = iter.next();
			assert this.name.equals(first.getName()) :
    			"GeoFeature RI violated :  segment does not meet req (name does not match).";
			if (last == null) {
				 assert this.start.equals(first.getP1()) : 
					 "GeoFeature RI violated : first segment p1 != start.";
			}
			else {
				assert last.getP2().equals(first.getP1()):
					"GeoFeature RI violated";
			}
			if (!iter.hasNext()) {
				assert first.getP2().equals(this.end):
					"GeoFeature RI violated : last segment p2 != end.";
			}
				    				
    	}
    }

	/**
	 * Constructs a new GeoFeature.
	 * 
	 * @requires gs != null
	 * @effects Constructs a new GeoFeature, r, such that r.name = gs.name &&
	 *          r.startHeading = gs.heading && r.endHeading = gs.heading && r.start
	 *          = gs.p1 && r.end = gs.p2
	 **/
	public GeoFeature(GeoSegment gs) {
		assert gs != null : "GeoFeature C'tor : gs received is null";
		this.name = gs.getName();
		this.startHeading = gs.getHeading();
		this.endHeading = gs.getHeading();
		this.start = gs.getP1();
		this.end = gs.getP2();
		this.length = gs.getLength();
		this.geoSegments = new ArrayList<GeoSegment>();
		this.geoSegments.add(gs);
		this.checkRep();
	}
	/**
	 * Constructs a new GeoFeature from an exisiting GeoFeature and a new GeoSegment
	 * @requires gf != null %%  gs != null && gs.name == gf.name
	 * @effects Constructs a new GeoFeature, r, such that r.name = gf.name &&
	 *          r.startHeading = gf.heading && r.endHeading = gs.heading && r.start
	 *          = gf.p1 && r.end = gs.p2
	 **/
	public GeoFeature(GeoFeature gf, GeoSegment gs) {
	this.name = gf.getName();
	this.startHeading = gf.getStartHeading();
	this.endHeading = gs.getHeading();
	this.start = gf.getStart();
	this.end = gs.getP2();
	this.length = gf.getLength() + gs.getLength();
	this.geoSegments = new ArrayList<GeoSegment>(gf.geoSegments); // hard copy
	this.geoSegments.add(gs);
	this.checkRep();
	}
	
	/**
	 * Returns name of geographic feature.
	 * 
	 * @return name of geographic feature
	 */
	public String getName() {
		this.checkRep();
		return this.name;
	}

	/**
	 * Returns location of the start of the geographic feature.
	 * 
	 * @return location of the start of the geographic feature.
	 */
	public GeoPoint getStart() {
		this.checkRep();
		return this.start;
	}

	/**
	 * Returns location of the end of the geographic feature.
	 * 
	 * @return location of the end of the geographic feature.
	 */
	public GeoPoint getEnd() {
		this.checkRep();
		return this.end;
	}

	/**
	 * Returns direction of travel at the start of the geographic feature.
	 * 
	 * @return direction (in standard heading) of travel at the start of the
	 *         geographic feature, in degrees.
	 */
	public double getStartHeading() {
		this.checkRep();
		return this.startHeading;
	}

	/**
	 * Returns direction of travel at the end of the geographic feature.
	 * 
	 * @return direction (in standard heading) of travel at the end of the
	 *         geographic feature, in degrees.
	 */
	public double getEndHeading() {
		this.checkRep();
		return this.endHeading;
	}

	/**
	 * Returns total length of the geographic feature, in kilometers.
	 * 
	 * @return total length of the geographic feature, in kilometers. NOTE: this is
	 *         NOT as-the-crow-flies, but rather the total distance required to
	 *         traverse the geographic feature. These values are not necessarily
	 *         equal.
	 */
	public double getLength() {
		this.checkRep();
		return this.length;
	}

	/**
	 * Creates a new GeoFeature that is equal to this GeoFeature with gs appended to
	 * its end.
	 * 
	 * @requires gs != null && gs.p1 = this.end && gs.name = this.name.
	 * @return a new GeoFeature r such that r.end = gs.p2 && r.endHeading =
	 *         gs.heading && r.length = this.length + gs.length
	 **/
	public GeoFeature addSegment(GeoSegment gs) {
		this.checkRep();
		assert gs!= null : "GeoFeature addSegment : gs is null";
		assert this.end.equals(gs.getP1()) :  "GeoFeature addSegment : end != gs.P1";
		assert this.name.equals(gs.getName()) : "GeoFeature addSegment : gs name doesnt match feature";
		try {
			return new GeoFeature(this, gs);
		}
		finally {
			this.checkRep();
		}
	}

	/**
	 * Returns an Iterator of GeoSegment objects. The concatenation of the
	 * GeoSegments, in order, is equivalent to this GeoFeature. All the GeoSegments
	 * have the same name.
	 * 
	 * @return an Iterator of GeoSegments such that
	 * 
	 *         <pre>
	 *      this.start        = a[0].p1 &&
	 *      this.startHeading = a[0].heading &&
	 *      this.end          = a[a.length - 1].p2 &&
	 *      this.endHeading   = a[a.length - 1].heading &&
	 *      this.length       = sum(0 <= i < a.length) . a[i].length &&
	 *      for all integers i
	 *          (0 <= i < a.length-1 => (a[i].name == a[i+1].name &&
	 *                                   a[i].p2d  == a[i+1].p1))
	 *         </pre>
	 * 
	 *         where <code>a[n]</code> denotes the nth element of the Iterator.
	 * @see homework1.GeoSegment
	 */
	public Iterator<GeoSegment> getGeoSegments() {
		this.checkRep();
		return this.geoSegments.iterator();
	}

	/**
	 * Compares the argument with this GeoFeature for equality.
	 * 
	 * @return o != null && (o instanceof GeoFeature) && (o.geoSegments and
	 *         this.geoSegments contain the same elements in the same order).
	 **/
	public boolean equals(Object o) {
		this.checkRep();
		return o != null && o instanceof GeoFeature &&
				((GeoFeature)o).geoSegments.equals((this.geoSegments));
	}

	/**
	 * Returns a hash code for this.
	 * 
	 * @return a hash code for this.
	 **/
	public int hashCode() {
		this.checkRep();
		return this.start.hashCode()+this.end.hashCode() + (int)this.length;
	}

	/**
	 * Returns a string representation of this.
	 * 
	 * @return a string representation of this.
	 **/
	public String toString() {
		this.checkRep();
		return this.name; //TODO MAKE THIS MORE INFORMATIVE IMPORTANT!!
	}
}
