package com.aprigoldcorporation.allculator.ui.activities;

import android.app.AlertDialog;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.fragments.CategoryFragment;
import com.aprigoldcorporation.allculator.ui.fragments.ExamFragment;
import com.aprigoldcorporation.allculator.ui.fragments.FavoriteFragment;
import com.aprigoldcorporation.allculator.ui.fragments.FinanceFragment;
import com.aprigoldcorporation.allculator.ui.fragments.HealthFragment;
import com.aprigoldcorporation.allculator.ui.fragments.InsuranceFragment;
import com.aprigoldcorporation.allculator.ui.fragments.MathFragment;
import com.aprigoldcorporation.allculator.ui.fragments.OthersFragment;
import com.aprigoldcorporation.allculator.ui.fragments.ReligionalFragment;
import com.aprigoldcorporation.allculator.ui.fragments.UnitConverterFragment;
import com.aprigoldcorporation.allculator.util.NetworkStateReceiver;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener
{

    //// TODO: 14.08.2017 Tanımlamalar buraya
    private NetworkStateReceiver networkStateReceiver;

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    RelativeLayout relativeLayoutSearchBar;
    private static final Integer[] tabIcons = {R.drawable.grid_w, R.drawable.star_w, android.R.drawable.ic_popup_sync, R.drawable.finance_w, R.drawable.exam_w, R.drawable.health_w, R.drawable.math_w, R.drawable.insurance_w, R.drawable.religional_w, R.drawable.others_w};

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private AdView mAdView;
    private ViewPager mViewPager;
    boolean isDoubleBackpressed =false;

//// TODO: 14.08.2017 Tanımlamalar buraya


    //// STOPSHIP: 14.08.2017 add tabıcon ic_launcher icon just temporary

    @Override
    public void onBackPressed()
   {
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//
//
//        mBuilder.setMessage(getString(R.string.DoYouWannaExit));
//
//        mBuilder.setNeutralButton(getString(R.string.RateUs), new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.aprigold.hesapla")));
//
//            }
//        });
//        mBuilder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//                finish();
//
//            }
//        });
//        mBuilder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                dialog.dismiss();
//            }
//        });
//        final AlertDialog alertDialog = mBuilder.create();
//
//        alertDialog.setOnShowListener(new DialogInterface.OnShowListener()
//        {
//            @Override
//            public void onShow(DialogInterface dialog)
//            {
//                alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorNo));
//                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorYes));
//                alertDialog.getButton(alertDialog.BUTTON_NEUTRAL).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorRateus));
//
//                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getResources().getColor(android.R.color.white));
//                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(android.R.color.holo_green_dark));
//                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(android.R.color.holo_red_dark));
//
//                Drawable drawableNo = getResources().getDrawable(R.drawable.no_exit2);
//                drawableNo.setBounds((int) (0),//left
//                        0,//top
//                        (int) (drawableNo.getIntrinsicWidth()),//right
//                        drawableNo.getIntrinsicHeight());//bottom
//                alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setCompoundDrawables(null, drawableNo, null, null);
//
//
//                Drawable drawableYes = getResources().getDrawable(
//                        R.drawable.yes_exit2);
//                drawableYes.setBounds((int) (0),//left
//                        0,//top
//                        (int) (drawableYes.getIntrinsicWidth()),//right
//                        drawableYes.getIntrinsicHeight());//bottom
//                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setCompoundDrawables(null, drawableYes, null, null);
//
//
//                Drawable drawableNeutral = getResources().getDrawable(
//                        R.drawable.five_start);
//                drawableNeutral.setBounds((int) (0),//left
//                        0,//top
//                        (int) (drawableNeutral.getIntrinsicWidth()),//right
//                        drawableNeutral.getIntrinsicHeight());//bottom
//                alertDialog.getButton(alertDialog.BUTTON_NEUTRAL).setCompoundDrawables(null, drawableNeutral, null, null);
//
//            }
//        });
//
//
//        alertDialog.show();


       if (isDoubleBackpressed) {
           super.onBackPressed();
           return;
       }

       this.isDoubleBackpressed = true;
//       Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
       LayoutInflater inflater = getLayoutInflater();
       View layout = inflater.inflate(R.layout.custom_toast,
               (ViewGroup) findViewById(R.id.custom_toast_container));

       TextView text = (TextView) layout.findViewById(R.id.text);
       text.setText("Çıkmak için 'Geri' tuşuna tekrar basın");

       final Toast toast = new Toast(getApplicationContext());
//       toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
       toast.setDuration(Toast.LENGTH_SHORT);
       toast.setView(layout);
       toast.show();

       new Handler().postDelayed(new Runnable() {

           @Override
           public void run() {
               toast.cancel();

           }
       }, 1200);

       new Handler().postDelayed(new Runnable() {

           @Override
           public void run() {
               isDoubleBackpressed=false;
           }
       }, 2000);



    }

    public void TabDegistir(int number)
    {
        mViewPager.setCurrentItem(number);
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container +
                ":" + mViewPager.getCurrentItem());
    }

    public void tabIconsAdd()
    {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
        tabLayout.getTabAt(7).setIcon(tabIcons[7]);
        tabLayout.getTabAt(8).setIcon(tabIcons[8]);
        tabLayout.getTabAt(9).setIcon(tabIcons[9]);


    }


    public void SetOnClickListenerlar()
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayoutSearchBar = (RelativeLayout) findViewById(R.id.RL_SearchBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabIconsAdd();

        // STOPSHIP: 13.08.2017 tab mode scrollable yaparak yana swipe a izin verdim.
        tabLayout.setTabMode(tabLayout.MODE_SCROLLABLE);


        //// STOPSHIP: 14.08.2017  action bar titles set. with viewpager
        final String[] ActionBarTitles = {
                getString(R.string.app_name), getString(R.string.Favorite_for_tab), getString(R.string.UnitConverter_for_tab), getString(R.string.Finances_for_tab), getString(R.string.Exam_for_tab), getString(R.string.Health_for_tab), getString(R.string.Math_for_tab), getString(R.string.Insurance_for_tab), getString(R.string.Religional_for_tab), getString(R.string.Others_for_tab)};
        final String[] Colors = {"#1d516a", "#f4483b", "#846add", "#2489b0", "#63c76a", "#ffa037", "#875546", "#ff5f3d", "#008bc4", "#9e1981"};
        appBarLayout.setBackgroundColor(Color.parseColor(Colors[0]));
        toolbar.setBackgroundColor(Color.parseColor(Colors[0]));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                tabLayout.setNextFocusRightId(position);

                getSupportActionBar().setTitle(ActionBarTitles[position]);
                appBarLayout.setBackgroundColor(Color.parseColor(Colors[position]));
                toolbar.setBackgroundColor(Color.parseColor(Colors[position]));
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });


        // STOPSHIP: 13.08.2017  advertisements added

        mAdView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed()
            {

                super.onAdClosed();

                Log.e("Adview", "onAdClosed: ");
            }

            @Override
            public void onAdFailedToLoad(int i)
            {
                super.onAdFailedToLoad(i);
                Log.e("Adbiew", "onAdFailedToLoad: ");

                mAdView.setVisibility(View.GONE);
            }


            @Override
            public void onAdLoaded()
            {
                mAdView.setVisibility(View.VISIBLE);

            }
        });

        // STOPSHIP: 13.08.2017 favorite tab start first

        mViewPager.setCurrentItem(0);


        SetOnClickListenerlar();


        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);

        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));


    }


    @Override
    protected void onResume()
    {

        super.onResume();
    }

    @Override
    public void networkAvailable()
    {

        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void networkUnavailable()
    {


    }

    public void onDestroy()
    {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {

            return true;
        }
        if (id == R.id.noAds)
        {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


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


                return new CategoryFragment();
            }
            if (position == 1)
            {

                return new FavoriteFragment();
            }
            if (position == 2)
            {

                return new UnitConverterFragment();

            }
            if (position == 3)
            {


                return new FinanceFragment();
            }
            if (position == 4)
            {

                return new ExamFragment();
            }
            if (position == 5)
            {


                return new HealthFragment();

            }
            if (position == 6)
            {

                return new MathFragment();
            }
            if (position == 7)
            {
                return new InsuranceFragment();
            }
            if (position == 8)
            {
                return new ReligionalFragment();

            }
            if (position == 9)
            {

                return new OthersFragment();
            }


            return new FavoriteFragment();

        }

        @Override
        public int getCount()
        {
            return 10;
        }


    }

}