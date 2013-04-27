package tw.h4.game.escape.disaster;

public class Earthquake extends Disaster {

	/**
	 * @see Disaster#getHpHit()
	 */
	@Override
	public int getHpHit(long t1, double lon1, double lat1, long t2,
	        double lon2, double lat2) {
		return 0;
	}

	/**
	 * @see Disaster#getReminderTime()
	 */
	@Override
	public int getReminderTime() {
		return 180;
	}
}
