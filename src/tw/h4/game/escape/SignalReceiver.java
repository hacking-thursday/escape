package tw.h4.game.escape;

import tw.h4.game.escape.pages.WarningActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SignalReceiver extends BroadcastReceiver {
    public static final String DISASTER_START = "tw.h4.game.escape.DISASTER_START";
    private static final String TAG = "SignalReceiver";

    @Override
    public void onReceive(Context ctx, Intent intent) {
        String action = intent.getAction();
        Debugger.d(TAG, "get action::" + action);
        if (DISASTER_START.equals(action)) {
            Intent activity = new Intent(ctx, WarningActivity.class);
            activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(activity);
            // TODO: launch WarningActivity
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // TODO: check the disaster start time
        }
    }

}
