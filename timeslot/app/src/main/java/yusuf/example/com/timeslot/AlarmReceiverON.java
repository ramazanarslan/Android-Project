package yusuf.example.com.timeslot;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiverON extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent ıntent)
    {
        Log.d("AlarmReceiverON", "ARRİVED ON");
        Toast.makeText(context, " RINGER_MODE_VIBRATE", Toast.LENGTH_LONG).show();

        AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }
}

