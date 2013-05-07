package tw.h4.game.escape;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {
    private static AlarmHelper mInstance = null;
    private Context ctx;
    private AlarmManager alarm = null;
    private PendingIntent operation = null;

    private AlarmHelper(Context context) {
        ctx = context.getApplicationContext();
        alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    }

    public static AlarmHelper getInstance(Context context) {
        if (null == mInstance) {
            synchronized (AlarmHelper.class) {
                mInstance = new AlarmHelper(context);
            }
        }
        return mInstance;
    }

    public void setNextAlarm(long time) {
        if (null != operation) {
            alarm.cancel(operation);
        }
        Intent intent = new Intent();
        intent.setAction(SignalReceiver.DISASTER_START);
        operation = PendingIntent.getBroadcast(ctx, 0, intent, 0);
        alarm.set(AlarmManager.RTC_WAKEUP, time, operation);
    }
}
