package tw.h4.game.escape.pages;

import tw.h4.game.escape.GamePreference;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GamePreference pref = GamePreference.getInstance(this);
		long startTime = pref.getEventTime();
		Intent intent = new Intent();
		long currTime = System.currentTimeMillis();
		if (-1 == startTime) {
			intent.setClass(this, StartGameActivity.class);
		} else if (currTime < startTime) {
			intent.setClass(this, WaitingActivity.class);
		} else {
			intent.setClass(this, WarningActivity.class);
		}

		startActivity(intent);
		finish();
	}

}
