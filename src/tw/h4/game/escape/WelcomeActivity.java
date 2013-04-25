package tw.h4.game.escape;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;

public class WelcomeActivity extends Activity {
	private static final long MAX_INTEVAL = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		// TODO: do not launch dialog, use activity only
		// maybe needs a blank activity do something like dispatcher.
		new AlertDialog.Builder(this)
		        .setTitle(R.string.app_name)
		        .setMessage(R.string.check_start_game)
		        .setPositiveButton(android.R.string.ok,
		                new DialogInterface.OnClickListener() {

			                @Override
			                public void onClick(DialogInterface dialog,
			                        int which) {
				                long time = System.currentTimeMillis()
				                        + (long) (MAX_INTEVAL * Math.random())
				                        * 1000;
				                AlarmHelper.getInstance(WelcomeActivity.this)
				                        .setNextAlarm(time);
				                GamePreference
				                        .getInstance(WelcomeActivity.this)
				                        .setEventTime(time);
			                }
		                })
		        .setNegativeButton(android.R.string.cancel,
		                new DialogInterface.OnClickListener() {

			                @Override
			                public void onClick(DialogInterface dialog,
			                        int which) {
				                // TODO: do nothing?
				                WelcomeActivity.this.finish();
			                }
		                }).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
