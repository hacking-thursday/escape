package tw.h4.game.escape;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GamePreference {
    private static final String PREFIX = "tw.h4.game.escape.";
    private static final String PREF_NAME = "tw.h4.game.escape_preferences";
    private static final String EVENT_TIME = PREFIX + "EVENT_TIME";
    private static final String EVENT_TYPE = PREFIX + "EVENT_TYPE";
    private static final String EVENT_LEVEL = PREFIX + "EVENT_LEVEL";
    private static final String PREV_RECORD_TIME = PREFIX + "PREV_RECORD_TIME";
    private static final String PREV_ELAPSED_TIME = PREFIX
            + "PREV_ELAPSED_TIME";
    private static final String PREV_LONGITUDE = PREFIX + "PREV_LONGITUDE";
    private static final String PREV_LATITUDE = PREFIX + "PREV_LATITUDE";
    private static final String CURR_HP = PREFIX + "CURR_HP";
    private static final String TIME_SPEED = PREFIX + "TIME_SPEED";

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

    public void setPrevRecordTime(long time) {
        Editor editor = pref.edit();
        editor.putLong(PREV_RECORD_TIME, time);
        editor.commit();
        Debugger.d("GamePreference", "record time::" + time);
    }

    public long getPrevRecordTime() {
        return pref.getLong(PREV_RECORD_TIME, -1l);
    }

    public void setPrevElapsedTime(long time) {
        Editor editor = pref.edit();
        editor.putLong(PREV_ELAPSED_TIME, time);
        editor.commit();
        Debugger.d("GamePreference", "elapsed time::" + time);
    }

    public long getPrevElapsedTime() {
        return pref.getLong(PREV_ELAPSED_TIME, 0);
    }

    public void setPrevLatitude(double latitude) {
        Editor editor = pref.edit();
        editor.putFloat(PREV_LATITUDE, (float) latitude);
        editor.commit();
    }

    public double getPrevLatitude() {
        return pref.getFloat(PREV_LATITUDE, 0);
    }

    public void setPrevLongitude(double longitude) {
        Editor editor = pref.edit();
        editor.putFloat(PREV_LONGITUDE, (float) longitude);
        editor.commit();
    }

    public double getPrevLongitude() {
        return pref.getFloat(PREV_LONGITUDE, 0);
    }

    public void setTimeSpeed(int speed) {
        Editor editor = pref.edit();
        editor.putInt(TIME_SPEED, speed);
        editor.commit();
    }

    public int getTimeSpeed() {
        return pref.getInt(TIME_SPEED, 1);
    }

    public void setEventType(int type) {
        Editor editor = pref.edit();
        editor.putInt(EVENT_TYPE, type);
        editor.commit();
    }

    public int getEventType() {
        return pref.getInt(EVENT_TYPE, 0);
    }

    public void setEventLevel(int type) {
        Editor editor = pref.edit();
        editor.putInt(EVENT_LEVEL, type);
        editor.commit();
    }

    public int getEventLevel() {
        return pref.getInt(EVENT_LEVEL, 0);
    }

    public void setCurrHp(int hp) {
        Editor editor = pref.edit();
        editor.putInt(CURR_HP, hp);
        editor.commit();
    }

    public int getCurrHp() {
        return pref.getInt(CURR_HP, 1000);
    }

    public void removeEvent() {
        Editor editor = pref.edit();
        editor.remove(EVENT_TIME);
        editor.remove(EVENT_TYPE);
        editor.remove(EVENT_LEVEL);
        editor.remove(PREV_RECORD_TIME);
        editor.remove(PREV_ELAPSED_TIME);
        editor.remove(PREV_LONGITUDE);
        editor.remove(PREV_LATITUDE);
        editor.remove(CURR_HP);
        editor.commit();
    }
}
