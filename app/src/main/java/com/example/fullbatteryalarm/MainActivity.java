package com.example.fullbatteryalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Ringtone ringtone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        ringtone= RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        BroadcastReceiver broadcastReceiverBattery= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Integer integerBatteryLevel=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                textView.setText(integerBatteryLevel.toString());

                if(integerBatteryLevel>99||integerBatteryLevel<10)
                {
                    ringtone.play();
                }

            }
        };

        registerReceiver(broadcastReceiverBattery,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }
    public void stopButton(View view)
    {
        ringtone.stop();

    }


}
