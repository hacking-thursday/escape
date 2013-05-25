package tw.h4.game.escape.pages;

import tw.h4.game.escape.AlarmHelper;
import tw.h4.game.escape.GamePreference;
import tw.h4.game.escape.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import tw.h4.game.escape.disaster.DisasterEngine;

public class StartGameActivity extends Activity {
    private static final long MAX_INTERVAL = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        Button btnStart = (Button) findViewById(R.id.btn_start_game);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Add 1 sec for at least has 1 sec.
                long time = System.currentTimeMillis()
                        + (long) (MAX_INTERVAL * Math.random() + 1) * 1000;
                AlarmHelper.getInstance(StartGameActivity.this).setNextAlarm(
                        time);
                int type = (int) (Math.random() * DisasterEngine.Type.getTotalCount());
                GamePreference pref = GamePreference.getInstance(StartGameActivity.this);
                pref.setEventTime(time);
                pref.setEventType(type);
                Intent intent = new Intent(StartGameActivity.this,
                        WaitingActivity.class);
                StartGameActivity.this.startActivity(intent);
                StartGameActivity.this.finish();
            }
        });
    }

}
