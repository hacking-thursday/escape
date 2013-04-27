package tw.h4.game.escape.disaster;

/**
 * This class defines a disaster behavior and member.
 * 
 * @author asil
 * 
 */
public abstract class Disaster {

	public interface DisaterType {
		public DisaterType getType(int level);
	}

	protected double mSrcLongitude;
	protected double mSrcLatitude;
	protected long mActionTime;
	protected int mLevel;

	/**
	 * This function define how many HP value will reduce during <code>t1</code>
	 * to <code>t2</code>.
	 * 
	 * @param t1
	 *            the previous time.
	 * @param lon1
	 *            the previous longitude.
	 * @param lat1
	 *            the previous latitude.
	 * @param t2
	 *            current time.
	 * @param lon2
	 *            current longitude.
	 * @param lat2
	 *            current latitude.
	 * @return A positive value or zero. If you are in a dangerous area, the
	 *         value will be positive, otherwise be zero.
	 */
	public abstract int getHpHit(long t1, double lon1, double lat1, long t2,
	        double lon2, double lat2);

	/**
	 * This function define how many time you have to escape this disaster.
	 * 
	 * @return The reminder time count by minutes
	 */
	public abstract int getReminderTime();
}
