package tw.h4.game.escape;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class WarningActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warning);
		GamePreference.getInstance(WarningActivity.this).removeEvent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
