package homework1;

/**
 * A GeoSegment models a straight line segment on the earth. GeoSegments are
 * immutable.
 * <p>
 * A compass heading is a nonnegative real number less than 360. In compass
 * headings, north = 0, east = 90, south = 180, and west = 270.
 * <p>
 * When used in a map, a GeoSegment might represent part of a street, boundary,
 * or other feature. As an example usage, this map
 * 
 * <pre>
 *  Trumpeldor   a
 *  Avenue       |
 *               i--j--k  Hanita
 *               |
 *               z
 * </pre>
 * 
 * could be represented by the following GeoSegments: ("Trumpeldor Avenue", a,
 * i), ("Trumpeldor Avenue", z, i), ("Hanita", i, j), and ("Hanita", j, k).
 * </p>
 * 
 * </p>
 * A name is given to all GeoSegment objects so that it is possible to
 * differentiate between two GeoSegment objects with identical GeoPoint
 * endpoints. Equality between GeoSegment objects requires that the names be
 * equal String objects and the end points be equal GeoPoint objects.
 * </p>
 *
 * <b>The following fields are used in the specification:</b>
 * 
 * <pre>
 *   name : String       // name of the geographic feature identified
 *   p1 : GeoPoint       // first endpoint of the segment
 *   p2 : GeoPoint       // second endpoint of the segment
 *   length : real       // straight-line distance between p1 and p2, in kilometers
 *   heading : angle     // compass heading from p1 to p2, in degrees
 * </pre>
 **/
public class GeoSegment {

	// TODO Write abstraction function and representation invariant

	// representation invariant:
	// object is not null
	// 0 <= heading < 360
	public static final double MIN_HEADING = 0;
	public static final double MAX_HEADING = 360;
	// abstraction function:
	// represent a segment: connection between 2 geographic points, along with its
	// name, length, and angle from the north.

	final String name;
	final GeoPoint p1;
	final GeoPoint p2;
	final double length;
	final double heading;

	/*
	 * Checks the rep. Inv.
	 * 
	 * @throws Assertion error if violated.
	 */
	private void checkRep() {
		assert this != null && this instanceof GeoSegment && this.heading < MAX_HEADING && this.heading >= MIN_HEADING
				&& this.name != null && this.p1 != null && this.p2 != null & this.length >= 0
				: "Rep. Inv. of class GeoSegment is violated.";
	}

	/**
	 * Constructs a new GeoSegment with the specified name and endpoints.
	 * 
	 * @requires name != null && p1 != null && p2 != null
	 * @effects constructs a new GeoSegment with the specified name and endpoints.
	 **/
	public GeoSegment(String name, GeoPoint p1, GeoPoint p2) {
		assert name != null && p1 != null && p2 != null : "GeoSegment C'tor : at least one of the parameters is null";
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
		this.length = p1.distanceTo(p2);
		this.heading = !p1.equals(p2) ? p1.headingTo(p2) : 0;

		this.checkRep();

	}

	/**
	 * Returns a new GeoSegment like this one, but with its endpoints reversed.
	 * 
	 * @return a new GeoSegment gs such that gs.name = this.name && gs.p1 = this.p2
	 *         && gs.p2 = this.p1
	 **/
	public GeoSegment reverse() {
		this.checkRep();
		return new GeoSegment(this.name, this.p2, this.p1);

	}

	/**
	 * Returns the name of this GeoSegment.
	 * 
	 * @return the name of this GeoSegment.
	 */
	public String getName() {
		this.checkRep();
		return this.name;
	}

	/**
	 * Returns first endpoint of the segment.
	 * 
	 * @return first endpoint of the segment.
	 */
	public GeoPoint getP1() {
		this.checkRep();
		return this.p1;
	}

	/**
	 * Returns second endpoint of the segment.
	 * 
	 * @return second endpoint of the segment.
	 */
	public GeoPoint getP2() {
		this.checkRep();
		return this.p2;
	}

	/**
	 * Returns the length of the segment.
	 * 
	 * @return the length of the segment, using the flat-surface, near the Technion
	 *         approximation.
	 */
	public double getLength() {
		this.checkRep();
		return this.length;
	}

	/**
	 * Returns the compass heading from p1 to p2.
	 * 
	 * @requires this.length != 0
	 * @return the compass heading from p1 to p2, in degrees, using the
	 *         flat-surface, near the Technion approximation.
	 **/
	public double getHeading() {

		this.checkRep();
		if (length == 0) {
			return 0;
		}
		return this.heading;
	}

	/**
	 * Compares the specified Object with this GeoSegment for equality.
	 * 
	 * @return gs != null && (gs instanceof GeoSegment) && gs.name = this.name &&
	 *         gs.p1 = this.p1 && gs.p2 = this.p2
	 **/
	public boolean equals(Object gs) {
		this.checkRep();
		boolean result = false;
		try {
			if ((gs != null) && (gs instanceof GeoSegment)) {
				GeoSegment gsCast = (GeoSegment) gs;
				result = ((gsCast != null) && (this.name.equals(gsCast.getName())) && (this.p1.equals(gsCast.getP1()))
						&& (this.p2.equals(gsCast.getP2())));
			}
			return result;
		} finally {
			this.checkRep();
		}
	}

	/**
	 * Returns a hash code value for this.
	 * 
	 * @return a hash code value for this.
	 **/
	public int hashCode() {
		// This implementation will work, but you may want to modify it
		// for improved performance.
		int hashValue = 1;
		this.checkRep();
		try {
			hashValue = (this.getP1().hashCode() * this.getP2().hashCode()) * (this.getName().length());
		} catch (Exception e) {
			hashValue = 1;
		}
		return hashValue;
	}

	/**
	 * Returns a string representation of this.
	 * 
	 * @return a string representation of this.
	 **/
	public String toString() {
		return String.format("Segment %s between p1: %s and p2: %s, has length of %f [Km]and heading of %f [deg] \n",
				this.name, this.p1.toString(), this.p2.toString(), this.length, this.heading);
	}

}
