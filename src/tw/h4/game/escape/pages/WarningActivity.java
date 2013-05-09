package tw.h4.game.escape.pages;

import java.lang.ref.WeakReference;
import java.util.Locale;

import tw.h4.game.escape.Debugger;
import tw.h4.game.escape.GamePreference;
import tw.h4.game.escape.R;
import tw.h4.game.escape.disaster.Disaster;
import tw.h4.game.escape.disaster.DisasterEngine;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WarningActivity extends Activity {
    private static final String TAG = "WarningActivity";
    private static final int FULL_HP = 1000;

    private static CounterHandler mHandler = null;
    private static final int UPDATE_TIME = 0x100;
    private int reminderTime = 0;
    private boolean needRecord = true;
    private TextView mTxtTimer;
    private ProgressBar mProg;
    private LocationManager mLocManager = null;
    private GamePreference pref = null;
    private Disaster disaster = null;
    private int currHp = FULL_HP;

    private static class CounterHandler extends Handler {
        private WeakReference<WarningActivity> mTarget = null;

        public CounterHandler(WarningActivity target) {
            mTarget = new WeakReference<WarningActivity>(target);
        }

        public void handleMessage(Message msg) {
            WarningActivity targetObj = mTarget.get();
            if (null == targetObj)
                return;
            switch (msg.what) {
            case UPDATE_TIME:
                // TODO: update time counter
                targetObj.showTimer();
                targetObj.reminderTime--;
                if (0 == targetObj.reminderTime) {
                    removeCallbacks(action);
                } else {
                    targetObj.startTimer(1000);
                }
                break;
            }
        }
    }

    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Debugger.d(TAG, "onLocationChanged");
            updateHp(location);
            if (null != mLocManager)
                mLocManager.removeUpdates(this);
        }

        public void onProviderDisabled(String provider) {
            Debugger.d(TAG, "onProviderDisabled((" + provider + "))");
        }

        public void onProviderEnabled(String provider) {
            Debugger.d(TAG, "onProviderEnabled((" + provider + "))");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Debugger.d(TAG, "onStatusChanged((" + provider + ")) status(("
                    + status + "))");
        }
    }

    private LocationListener mWifiLocListener = new MyLocationListener();
    private LocationListener mGpsLocListener = new MyLocationListener();

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
        case R.id.btn_cancel_game:
            GamePreference pref = GamePreference.getInstance(this);
            pref.removeEvent();
            mHandler.removeCallbacks(action);
            needRecord = false;
            break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        mHandler = new CounterHandler(this);
        mTxtTimer = (TextView) findViewById(R.id.warn_time);
        mProg = (ProgressBar) findViewById(R.id.pro_hp);
        // TODO: get location here?
        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        pref = GamePreference.getInstance(WarningActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        long currTime = System.currentTimeMillis();
        int timeSpeed = pref.getTimeSpeed();
        long prevElapsedTime = pref.getPrevElapsedTime();
        long prevRecodeTime = pref.getPrevRecordTime();
        int type = pref.getEventType();
        if (null == disaster)
            disaster = (new DisasterEngine(this)).getDisaster(type);

        long escapeTime = disaster.getEscapeTime(), elaspsedTime = 0;
        Debugger.d(TAG, "name:" + disaster.getDisasterName(this));
        if (-1 == prevRecodeTime) {
            prevRecodeTime = currTime;
            prevElapsedTime = 0;
        } else {
            elaspsedTime = prevElapsedTime + (currTime - prevRecodeTime)
                    * timeSpeed;
            if (elaspsedTime > escapeTime) {
                // TODO: game over
                Debugger.d(TAG, "Game over:" + elaspsedTime);
                return;
            }
        }
        long reminderTimeMS = escapeTime - elaspsedTime;
        Debugger.d(TAG, "reminder time:" + reminderTimeMS);
        reminderTime = (int) (reminderTimeMS / 1000);
        pref.setPrevElapsedTime(elaspsedTime);
        pref.setPrevRecordTime(currTime);
        showTimer();
        reminderTime--;
        startTimer(1000 - (reminderTimeMS % 1000));
        // get location here
        if (null == mLocManager) {
            return;
        }
        Debugger.d(TAG, "wifi:" + mWifiLocListener + ", gps:" + mGpsLocListener);
        try {
            mLocManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, mWifiLocListener);
        } catch (Exception e) {
            Debugger.d(TAG, e.toString());
        }

        try {
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                    0, mGpsLocListener);
        } catch (Exception e) {
            Debugger.d(TAG, e.toString());
        }
    }

    protected void onPause() {
        if (needRecord) {
            mHandler.removeCallbacks(action);
            // TODO: save elapsed time
            long currTime = System.currentTimeMillis();
            GamePreference pref = GamePreference
                    .getInstance(WarningActivity.this);
            int timeSpeed = pref.getTimeSpeed();
            long prevElapsedTime = pref.getPrevElapsedTime();
            long prevRecodeTime = pref.getPrevRecordTime();
            pref.setPrevRecordTime(currTime);
            pref.setPrevElapsedTime(prevElapsedTime
                    + (currTime - prevRecodeTime) * timeSpeed);
        }
        super.onPause();
    }

    private static Runnable action = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = UPDATE_TIME;
            mHandler.sendMessage(msg);
        }
    };

    private void startTimer(long interval) {
        mHandler.postDelayed(action, interval);
    }

    public void showTimer() {
        int hr = reminderTime / 3600;
        int min = (reminderTime % 3600) / 60;
        int sec = reminderTime % 60;
        String timeString = String.format(Locale.US, "%02d:%02d:%02d", hr, min,
                sec);
        mTxtTimer.setText(timeString);
    }

    private void updateHp(Location location) {
        if (null == location || null == disaster) {
            return;
        }
        Debugger.d(TAG, "get location (" + location.getLatitude() + ", "
                + location.getLongitude() + ")");
        double prevLat = pref.getPrevLatitude();
        double prevLon = pref.getPrevLongitude();
        long prevTime = pref.getPrevRecordTime();
        long elapsedTime = pref.getPrevElapsedTime();
        long currTime = System.currentTimeMillis();
        double currLat = location.getLatitude();
        double currLon = location.getLongitude();
        int timeSpeed = pref.getTimeSpeed();

        currHp -= disaster.getHpHit(prevTime, prevLon, prevLat, currTime,
                currLon, currLat);
        // TODO: set a progress bar as hit-point.
        mProg.setProgress(currHp);
        // TODO: restore info here.
        pref.setPrevRecordTime(currTime);
        pref.setPrevLatitude(currLat);
        pref.setPrevLongitude(currLon);
        elapsedTime += (currTime - prevTime) * timeSpeed;
        pref.setPrevElapsedTime(elapsedTime);
    }
}
