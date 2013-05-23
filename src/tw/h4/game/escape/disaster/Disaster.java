package tw.h4.game.escape.disaster;

import android.content.Context;

/**
 * This class defines a disaster behavior and member.
 *
 * @author asil
 */
public abstract class Disaster {
    protected double mSrcLongitude;
    protected double mSrcLatitude;
    protected long mActionTime;
    protected int mLevel;

    /**
     * This function define how many HP value will reduce during <code>t1</code>
     * to <code>t2</code>.
     *
     * @param t1   the previous time.
     * @param lon1 the previous longitude.
     * @param lat1 the previous latitude.
     * @param t2   current time.
     * @param lon2 current longitude.
     * @param lat2 current latitude.
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
    public abstract long getEscapeTime();

    /**
     * Get current disaster type name.
     *
     * @param ctx use to get Resources.
     * @return The name of current disaster.
     */
    public abstract String getDisasterName(Context ctx);
}
