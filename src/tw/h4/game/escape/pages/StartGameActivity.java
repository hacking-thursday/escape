package tw.h4.game.escape.pages;

import tw.h4.game.escape.AlarmHelper;
import tw.h4.game.escape.GamePreference;
import tw.h4.game.escape.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartGameActivity extends Activity {
	private static final long MAX_INTEVAL = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_game);
		// TODO: do not launch dialog, use activity only
		// maybe needs a blank activity do something like dispatcher.
		Button btnStart = (Button) findViewById(R.id.btn_start_game);
		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				long time = System.currentTimeMillis()
				        + (long) (MAX_INTEVAL * Math.random()) * 1000;
				AlarmHelper.getInstance(StartGameActivity.this).setNextAlarm(
				        time);
				GamePreference.getInstance(StartGameActivity.this)
				        .setEventTime(time);
				Intent intent = new Intent(StartGameActivity.this,
				        WaitingActivity.class);
				StartGameActivity.this.startActivity(intent);
				StartGameActivity.this.finish();
			}
		});
	}

}
