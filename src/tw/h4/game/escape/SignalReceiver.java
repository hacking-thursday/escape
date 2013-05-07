package tw.h4.game.escape;

import tw.h4.game.escape.pages.WarningActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SignalReceiver extends BroadcastReceiver {
    public static final String DISASTER_START = "tw.h4.game.escape.DISASTER_START";
    public static final String TIME_UP = "tw.h4.game.escape.TIME_UP";
    private static final String TAG = "SignalReceiver";

    @Override
    public void onReceive(Context ctx, Intent intent) {
        String action = intent.getAction();
        Debugger.d(TAG, "get action::" + action);
        if (DISASTER_START.equals(action)) {
            // launch WarningActivity
            Intent activity = new Intent(ctx, WarningActivity.class);
            activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(activity);
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // TODO: check the disaster start time
        }
    }

}
