package tw.h4.game.escape.disaster;

public abstract class Disater {

	public interface DisaterType {
		public DisaterType getLevel();
	}

	protected double mSrcLongitude;
	protected double mSrcLatitude;
	protected long mActionTime;
	protected int mLevel;

	public abstract int getHpHit(long t1, double lon1, double lat1, long t2,
	        double lon2, double lat2);
}
