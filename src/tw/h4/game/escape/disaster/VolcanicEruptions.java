package tw.h4.game.escape.disaster;

import android.content.Context;

public class VolcanicEruptions extends Disaster {

    /**
     * @see Disaster#getHpHit()
     */
    @Override
    public int getHpHit(long t1, double lon1, double lat1, long t2,
                        double lon2, double lat2) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @see Disaster#getEscapeTime()
     */
    @Override
    public long getEscapeTime() {
        return 3600000l;
    }

    /**
     * @see Disaster#getDisasterName(Context ctx)
     */
    @Override
    public String getDisasterName(Context ctx) {
        return "Volcanic eruptions";
    }
}
