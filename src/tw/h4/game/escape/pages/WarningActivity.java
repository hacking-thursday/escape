package tw.h4.game.escape.pages;

import java.lang.ref.WeakReference;

import tw.h4.game.escape.GamePreference;
import tw.h4.game.escape.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

public class WarningActivity extends Activity {

    private static CounterHandler mHandler = null;
    private static final int UPDATE_TIME = 0x100;

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
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        mHandler = new CounterHandler(this);
        GamePreference.getInstance(WarningActivity.this).removeEvent();
        startTimer();
    }

    private void startTimer() {
        Message msg = new Message();
        msg.what = UPDATE_TIME;
        mHandler.sendMessage(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
