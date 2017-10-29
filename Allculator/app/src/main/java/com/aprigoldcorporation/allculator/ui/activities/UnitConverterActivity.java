package com.aprigoldcorporation.allculator.ui.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.util.NetworkStateReceiver;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
public class UnitConverterActivity extends AppCompatActivity implements View.OnClickListener ,NetworkStateReceiver.NetworkStateReceiverListener
{
    private AdView adViewUC;

    View pickable_from, pickable_to;
    TextView txt_input, txt_result, txt_from_symbol, txt_to_symbol, from_unit_name, to_unit_name;

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    List<String> unitDescs, unitSymbols;
    List<Double> unitValues;
//    private static final Integer[] productIcons = {R.drawable.weight, R.drawable.length, R.drawable.volume, R.drawable.temperature, R.drawable.power, R.drawable.area, R.drawable.pressure, R.drawable.time};

    int array_id;
    private NetworkStateReceiver networkStateReceiver;

    public static void getInstance(Activity activity, int arrayId, String productname, int image_nu)
    {
        Intent intent = new Intent(activity, UnitConverterActivity.class);
        intent.putExtra("array_id", arrayId);
        intent.putExtra("product_name", productname);
        intent.putExtra("image_nu", image_nu);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter_draft);

        toolbar = (Toolbar) findViewById(R.id.toolbarUC);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarUC);
        setSupportActionBar(toolbar);

        adViewUC = (AdView) findViewById(R.id.adViewUC);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adViewUC.loadAd(adRequest);


        adViewUC.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed()
            {

                super.onAdClosed();

            }

            @Override
            public void onAdFailedToLoad(int i)
            {
                super.onAdFailedToLoad(i);

                adViewUC.setVisibility(View.GONE);
            }


            @Override
            public void onAdLoaded()
            {
                adViewUC.setVisibility(View.VISIBLE);

            }
        });


        array_id = getIntent().getIntExtra("array_id", 0);
        if (array_id == 0)
        {
            Toast.makeText(this, "HATA ! spinner dolduralamadı", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getSupportActionBar() != null)
        {
//
//            Drawable drawableNo = getResources().getDrawable(productIcons[getIntent().getIntExtra("image_nu", 0)]);
//            drawableNo.setBounds((int) (0),//left
//                    0,//top
//                    (int) (drawableNo.getIntrinsicWidth()),//right
//                    drawableNo.getIntrinsicHeight());//bottom
            int white = getResources().getColor(android.R.color.white);
            String title = getIntent().getStringExtra("product_name");
            getSupportActionBar().setTitle(title);
//            getSupportActionBar().setIcon(drawableNo);getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            ;

        }
        txt_input = (TextView) findViewById(R.id.unit_converter_txt_input);
        txt_input.setText("");
        txt_result = (TextView) findViewById(R.id.unit_converter_txt_result);
        txt_result.setText("");
        from_unit_name = (TextView) findViewById(R.id.unit_converter_from_unit_name);
        to_unit_name = (TextView) findViewById(R.id.unit_converter_to_unit_name);

        txt_from_symbol = (TextView) findViewById(R.id.unit_converter_input_from_description);
        txt_from_symbol.setText("");
        txt_to_symbol = (TextView) findViewById(R.id.unit_converter_input_to_description);
        txt_to_symbol.setText("");

        String[] baseArray = getResources().getStringArray(array_id);
        unitDescs = new ArrayList<>();
        unitSymbols = new ArrayList<>();
        unitValues = new ArrayList<>();
        for (String item : baseArray)
        {
            String[] splittedArray = item.split("\\*");
            unitDescs.add(splittedArray[0]);
            unitSymbols.add(splittedArray[1]);
            Double unitValue = getDoubleValueOfString(splittedArray[2]);
            if (unitValue == Double.NaN)
                return;
            unitValues.add(unitValue);
        }
        ArrayAdapter<String> adapterDesc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unitDescs);

        pickable_from = findViewById(R.id.unit_converter_btn_from);
        pickable_from.setOnClickListener(this);
        pickable_to = findViewById(R.id.unit_converter_btn_to);
        pickable_to.setOnClickListener(this);

        from_unit_name.setText(unitDescs.get(0));
        txt_from_symbol.setText(unitSymbols.get(0));
        to_unit_name.setText(unitDescs.get(1));
        txt_to_symbol.setText(unitSymbols.get(1));

        calculate();

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);

        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }



    @Override
    public void networkAvailable()
    {

        final AdRequest adRequest = new AdRequest.Builder().build();
        adViewUC.loadAd(adRequest);

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
        getMenuInflater().inflate(R.menu.menu_each_callculate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {


        int id = item.getItemId();

        if (id == android.R.id.home)
        {

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_favori)
        {

            return true;
        }
        if (id == R.id.info)
        {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void numberClicked(View v)
    {
        String number = ((Button) v).getText().toString();
        txt_input.append(number);

        calculate();
    }

    private void calculate()
    {
        if (txt_input.length() == 0)
            return;

        int from = unitDescs.indexOf(from_unit_name.getText().toString());
        int to = unitDescs.indexOf(to_unit_name.getText().toString());

        Double result = Double.NaN;
        Double userInput = getDoubleValueOfString(txt_input.getText().toString());
        result = userInput * unitValues.get(from) * (1.0 / unitValues.get(to));
        txt_result.setText(App.doubleFormat(13, result));
    }

    private Double getDoubleValueOfString(String s)
    {
        Double number = Double.NaN;
        try
        {
            number = Double.valueOf(s);
        } catch (NumberFormatException e)
        {
            Toast.makeText(this, "Numara dönüştürelemedi", Toast.LENGTH_SHORT).show();
        }
        return number;
    }

    public void dotClicked(View v)
    {
        if (txt_input.getText().toString().contains("."))
            return;

        if (txt_input.length() == 0)
            txt_input.append("0.");
        else
            txt_input.append(".");

        calculate();
    }

    public void backwardsClicked(View v)
    {
        if (txt_input.length() == 0)
            return;

        if (txt_input.length() == 1)
            txt_result.setText("");

        String currentText = txt_input.getText().toString();
        txt_input.setText(currentText.substring(0, currentText.length() - 1));

        calculate();
    }

    public void reset(View v)
    {
        txt_input.setText("");
        txt_result.setText("");
    }

    public void reverseClicked(View v)
    {
        String fromName = from_unit_name.getText().toString();
        String toName = to_unit_name.getText().toString();
        String fromSymbol = txt_from_symbol.getText().toString();
        String toSymbol = txt_to_symbol.getText().toString();
        from_unit_name.setText(toName);
        to_unit_name.setText(fromName);
        txt_from_symbol.setText(toSymbol);
        txt_to_symbol.setText(fromSymbol);

        calculate();
    }

    public void copyClicked(View v)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("BirimSonuc", txt_result.getText());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Sonuç kopyalandı", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        SearchActivity.getInstance(UnitConverterActivity.this, v.getId(), array_id);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SearchActivity.SEARCH_ACTIVITY_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle extras = data.getExtras();
                int index = extras.getInt("selectedUnitIndex");
                if (extras.getInt("sender_id") == pickable_from.getId())
                {
                    from_unit_name.setText(unitDescs.get(index));
                    txt_from_symbol.setText(unitSymbols.get(index));
                }
                else
                {
                    to_unit_name.setText(unitDescs.get(index));
                    txt_to_symbol.setText(unitSymbols.get(index));
                }
            }
            calculate();
        }
    }
}
