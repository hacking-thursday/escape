package tw.h4.game.escape;

import android.util.Log;

public class Debugger {
    private static final String TAG = "Escape-";
    private static final int VERBOSE = 0x0001;
    private static final int DEBUG = 0x0002;
    private static final int INFO = 0x0004;
    private static final int WARN = 0x0008;
    private static final int ERROR = 0x0010;
    private static final int LOG_LEVEL = 0x001F;

    public static void v(String tag, String msg) {
        if (0 != (LOG_LEVEL & VERBOSE))
            Log.v(TAG + tag, msg);
    }

    public static void d(String tag, String msg) {
        if (0 != (LOG_LEVEL & DEBUG))
            Log.d(TAG + tag, msg);
    }

    public static void i(String tag, String msg) {
        if (0 != (LOG_LEVEL & INFO))
            Log.i(TAG + tag, msg);
    }

    public static void w(String tag, String msg) {
        if (0 != (LOG_LEVEL & WARN))
            Log.w(TAG + tag, msg);
    }

    public static void e(String tag, String msg) {
        if (0 != (LOG_LEVEL & ERROR))
            Log.e(TAG + tag, msg);
    }
}
