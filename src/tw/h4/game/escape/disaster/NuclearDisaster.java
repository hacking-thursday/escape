package tw.h4.game.escape.disaster;

import android.content.Context;

public class NuclearDisaster extends Disaster {

    /**
     * @see Disaster#getHpHit()
     */
    @Override
    public int getHpHit(long t1, double lon1, double lat1, long t2,
            double lon2, double lat2) {
        // TODO: complete this function
        return 0;
    }

    /**
     * @see Disaster#getEscapeTime()
     */
    @Override
    public long getEscapeTime() {
        return 18000000l;
    }

    /**
     * @see Disaster#getDisasterName(Context ctx)
     */
    @Override
    public String getDisasterName(Context ctx) {
        return "Nuclear disaster";
    }
}
