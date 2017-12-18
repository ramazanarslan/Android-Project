package yusuf.example.com.timeslot;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity

{
    AlarmManager alarmManagerON;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManagerOFF;
    Intent intent2;
    PendingIntent pendingIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnOFF = (Button) findViewById(R.id.btnOFF);
        final Button btnON = (Button) findViewById(R.id.btnON);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false))
        {
            // <---- run your one time code here
//Bir kere default değerleri versin sonra tekrar kontrol etmesin

            SharedPreferences sharedPreferences = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("vibrateOn-Time-Hour", 12);
            editor.putInt("vibrateOn-Time-Min", 0);
            editor.putInt("vibrateOff-Time-Hour", 10);
            editor.putInt("vibrateOff-Time-Min", 0);
            editor.commit();


            // mark first time has runned.
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("firstTime", true);
            edit.commit();
        }


        SharedPreferences sp = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
        final int sharedOFFHour = sp.getInt("vibrateOff-Time-Hour", -1);
        final int sharedOFFMin = sp.getInt("vibrateOff-Time-Min", -1);
        int sharedONHour = sp.getInt("vibrateOn-Time-Hour", -1);
        int sharedONMin = sp.getInt("vibrateOn-Time-Min", -1);

//        Açılırken butonları sistemden kayıtlı olan Sharedreference lardan alıyor
        btnOFF.setText(sharedOFFHour + ":" + sharedOFFMin);
        btnON.setText(sharedONHour + ":" + sharedONMin);


//        btnON click eventi
        btnON.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences sp = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
                int sharedONHour = sp.getInt("vibrateOn-Time-Hour", 13);
                int sharedONMin = sp.getInt("vibrateOn-Time-Min", 0);

//                Butona basınca alert dialog çıkmakta
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {

//                        Burda seçilen zaman ile ilgili yapılacakları yazdık
//                        örneğin SharedRef e kaydedilmesi. Önceki alarmın iptali ve yeni alarmın tekrar oluşturulması
                        SharedPreferences sharedPreferences = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("vibrateOn-Time-Hour", selectedHour);
                        editor.putInt("vibrateOn-Time-Min", selectedMinute);
                        editor.commit();

                        btnON.setText(selectedHour + ":" + selectedMinute);

                        alarmManagerON.cancel(pendingIntent);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);
                        calendar.set(Calendar.SECOND, 0);
                        alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
                        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


                    }
                }, sharedONHour, sharedONMin, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });


        btnOFF.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                SharedPreferences sp = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
                int sharedOFFHour = sp.getInt("vibrateOff-Time-Hour", 12);
                int sharedOFFMin = sp.getInt("vibrateOff-Time-Min", 0);


                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences("time_info_log", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("vibrateOff-Time-Hour", selectedHour);
                        editor.putInt("vibrateOff-Time-Min", selectedMinute);
                        editor.commit();
                        btnOFF.setText(selectedHour + ":" + selectedMinute);

                        alarmManagerOFF.cancel(pendingIntent2);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);
                        calendar.set(Calendar.SECOND, 0);
                        alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
                        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(), 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);




                    }
                }, sharedOFFHour, sharedOFFMin, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        Calendar calendarON = Calendar.getInstance();
        calendarON.set(Calendar.HOUR_OF_DAY, sp.getInt("vibrateOn-Time-Hour", -1));
        calendarON.set(Calendar.MINUTE, sp.getInt("vibrateOn-Time-Min", -1));
        calendarON.set(Calendar.SECOND, 0);
        alarmManagerON = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(getBaseContext(), AlarmReceiverON.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerON.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarON.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        Calendar calendarOFF = Calendar.getInstance();
        calendarOFF.set(Calendar.HOUR_OF_DAY, sp.getInt("vibrateOff-Time-Hour", -1));
        calendarOFF.set(Calendar.MINUTE, sp.getInt("vibrateOff-Time-Min", -1));
        calendarOFF.set(Calendar.SECOND, 0);
        alarmManagerOFF = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent2 = new Intent(getBaseContext(), AlarmReceiverOFF.class);
        pendingIntent2 = PendingIntent.getBroadcast(getBaseContext(), 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManagerOFF.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarOFF.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);




    }


}
