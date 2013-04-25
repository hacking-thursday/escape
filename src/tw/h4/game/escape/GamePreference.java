package tw.h4.game.escape;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GamePreference {
	private static final String PREFIX = "tw.h4.game.escape.";
	private static final String PREF_NAME = PREFIX + "preference";
	private static final String EVENT_TIME = PREFIX + "EVENT_TIME";
	private static final String EVENT_TYPE = PREFIX + "EVENT_TYPE";

	private SharedPreferences pref;
	private static GamePreference mInstance = null;

	private GamePreference(Context context) {
		pref = context.getApplicationContext().getSharedPreferences(PREF_NAME,
		        Context.MODE_PRIVATE);
	}

	public static GamePreference getInstance(Context context) {
		if (null == mInstance) {
			synchronized (GamePreference.class) {
				mInstance = new GamePreference(context);
			}
		}
		return mInstance;
	}

	public void setEventTime(long time) {
		Editor editor = pref.edit();
		editor.putLong(EVENT_TIME, time);
		editor.commit();
	}

	public long getEventTime() {
		return pref.getLong(EVENT_TIME, -1);
	}

	public void setEventType(int type) {
		Editor editor = pref.edit();
		editor.putInt(EVENT_TYPE, type);
		editor.commit();
	}

	public void removeEvent() {
		Editor editor = pref.edit();
		editor.remove(EVENT_TIME);
		editor.remove(EVENT_TYPE);
		editor.commit();
	}
}
