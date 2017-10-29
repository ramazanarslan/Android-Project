package com.aprigoldcorporation.examtimer;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.AlteredCharSequence;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pascalwelsch.holocircularprogressbar.HoloCircularProgressBar;

public class MainActivity extends AppCompatActivity
{
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 133;
    private AdView mAdView;
    TabLayout tabLayout;
    private int mcc, mnc;

    private TabType tabType = TabType.ICONS_ONLY;
    public static final String TAB_TYPE = "tab_type";
    private final String[] tabArray = {"Saved", "Timer", "Clock"};//Tab title array
    private final String[] tabArrayTR = {"Kayıtlı", "Zamanlayıcı", "Saat"};//Tab title array
    private static final Integer[] tabIcons = {R.drawable.saved, R.drawable.timer, R.drawable.clock};
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public void TabDegistir(int index, String label, int gelendk, int item1, int item2, int item3, int item4)
    {


        mViewPager.setCurrentItem(index);
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container +
                ":" + mViewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct // class and call the method:
        if (mViewPager.getCurrentItem() == 0 && page != null)
        {
            ((ListViewFragment) page).SavedOlustur(label, gelendk, item1, item2, item3, item4);
        }
        else if (mViewPager.getCurrentItem() == 1 && page != null)
        {
            // ((GridViewFragment)page).fslmnd
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabType
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setUpCustomTabs();
        mViewPager.setCurrentItem(1);


        /*Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);
        findViewById(R.id.start_alarm_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                String getInterval = editText.getText().toString().trim();//get interval from edittext
                String getInterval = "30";//get interval from edittext
                int interval = Integer.parseInt(getInterval);
                //check interval should not be empty and 0
                if (!getInterval.equals("") && !getInterval.equals("0"))
                    //finally trigger alarm manager
                    triggerAlarmManager(interval);

            }
        });

        //set on click over stop alarm button
        findViewById(R.id.stop_alarm_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Stop alarm manager
                stopAlarmManager();
            }
        });*/

    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);


        mBuilder.setMessage(getString(R.string.DoYouWannaExit));
        mBuilder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                finish();

            }
        });
        mBuilder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = mBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialog)
            {
                alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorTransparentBlue));
                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorTransparentRed));
            }
        });

        alertDialog.show();

    }

    private void setUpCustomTabs()
    {
        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator))
        {
            mcc = Integer.parseInt(networkOperator.substring(0, 3));
            mnc = Integer.parseInt(networkOperator.substring(3));
        }
        if (mcc != 286)
        {
            for (int i = 0; i < tabArray.length; i++)
            {
                TextView customTab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);//get custom view
                customTab.setText(tabArray[i]);//set text over view
                customTab.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[i], 0, 0);//set icon above the view
                TabLayout.Tab tab = tabLayout.getTabAt(i);//get tab via position
                if (tab != null)
                    tab.setCustomView(customTab);//set custom view
            }

        }
        else
        {
            for (int i = 0; i < tabArrayTR.length; i++)
            {
                TextView customTab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);//get custom view
                customTab.setText(tabArrayTR[i]);//set text over view
                customTab.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[i], 0, 0);//set icon above the view
                TabLayout.Tab tab = tabLayout.getTabAt(i);//get tab via position
                if (tab != null)
                    tab.setCustomView(customTab);//set custom view
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     * <p>
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {


        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
            {

                return new ListViewFragment();
            }
            if (position == 1)
            {
                return new GridViewFragment();
            }
            if (position == 2)
            {
                return new tab3();

            }
            return new tab3();

        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
            /**/


        @Override
        public int getCount()
        {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "SAVED";
                case 1:
                    return "TIMER";
                case 2:
                    return "CLOCK";
            }
            return null;
        }
    }


    //Trigger alarm manager with entered time interval
    public void triggerAlarmManager(int alarmTriggerTime)
    {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, (alarmTriggerTime * 1000), pendingIntent);//sst alarm manager with entered timer by converting into milliseconds

        Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
    }

    //Stop/Cancel alarm manager
    public void stopAlarmManager()
    {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);//cancel the alarm manager of the pending intent


        //Stop the Media Player Service to stop sound
        stopService(new Intent(MainActivity.this, AlarmSoundService.class));

        //remove the notification from notification tray
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AlarmNotificationService.NOTIFICATION_ID);

        Toast.makeText(this, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show();
    }
}
