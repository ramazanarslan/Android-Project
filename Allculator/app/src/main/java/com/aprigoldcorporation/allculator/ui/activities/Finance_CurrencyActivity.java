package com.aprigoldcorporation.allculator.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Xml;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.models.CurrencyAdapterModel;
import com.aprigoldcorporation.allculator.models.CurrencyFeed;
import com.aprigoldcorporation.allculator.ui.adapter.CurrencyAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Finance_CurrencyActivity extends AppCompatActivity implements View.OnClickListener {

    private String URL_TCMB = "http://www.tcmb.gov.tr/kurlar/today.xml";
    private static final String ns = null;

    List<CurrencyFeed> currencyList;
    String[] currencyNames;
    String updatedDate;
    CurrencyAdapter adapter;

    int index1;
    int index2;

    TextView txt_from_currency, txt_to_currency, txt_last_updated_date;
    EditText etxt_currency_from, etxt_currency_to;
    RecyclerView recyler_currency_recent;
    ImageView pin_currency, img_reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_currency);

        txt_from_currency = (TextView) findViewById(R.id.txt_currency_from);
        txt_from_currency.setOnClickListener(this);
        txt_to_currency = (TextView) findViewById(R.id.txt_to_currency);
        txt_to_currency.setOnClickListener(this);
        txt_last_updated_date = (TextView) findViewById(R.id.currency_last_updated_date);
        etxt_currency_from = (EditText) findViewById(R.id.etxt_currency_from);
        etxt_currency_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate();
            }
        });
        etxt_currency_to = (EditText) findViewById(R.id.etxt_currency_to);
        pin_currency = (ImageView) findViewById(R.id.img_pin_currency);
        pin_currency.setOnClickListener(this);
        img_reverse = (ImageView) findViewById(R.id.img_currency_reverse);
        img_reverse.setOnClickListener(this);

        recyler_currency_recent = (RecyclerView) findViewById(R.id.recycler_recent_currencies);
        recyler_currency_recent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new CurrencyAdapter();
        recyler_currency_recent.setAdapter(adapter);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dowloadCurrencies();
    }

    private void fillLayouts() {
        txt_last_updated_date.setText(updatedDate);

        currencyList.add(new CurrencyFeed("TL","Turkish Liras","1"));
        currencyNames = new String[currencyList.size()];
        for (int i = 0; i < currencyList.size(); i++){
            currencyNames[i] = currencyList.get(i).getCurrencyName();
        }

        txt_from_currency.setText(currencyList.get(0).getCurrencyName());
        index1 = 0;
        txt_to_currency.setText(currencyList.get(1).getCurrencyName());
        index2 = 1;
    }

    private void calculate() {
        if (etxt_currency_from.length() == 0)
            return;

        String from = txt_from_currency.getText().toString();
        String to = txt_to_currency.getText().toString();

        double result;
        if (to.equals("Turkish Liras")){
            double number = Double.valueOf(etxt_currency_from.getText().toString());
            double rate = Double.valueOf(currencyList.get(index1).getCurrencyRate());
            result = number * rate;
        }
        else if (from.equals("Turkish Liras")){
            double number = Double.valueOf(etxt_currency_from.getText().toString());
            double rate = Double.valueOf(currencyList.get(index2).getCurrencyRate());
            result = number / rate;
        }
        else {
            double number = Double.valueOf(etxt_currency_from.getText().toString());
            double rate1 = Double.valueOf(currencyList.get(index1).getCurrencyRate());
            double rate2 = Double.valueOf(currencyList.get(index2).getCurrencyRate());
            result = number * rate2 / rate1;
        }
        etxt_currency_to.setText(App.doubleFormat(4,result) + "");
    }

    private void addToRecylerView() {
        CurrencyAdapterModel model = new CurrencyAdapterModel();
        model.setTxt_first(txt_from_currency.getText().toString());
        model.setTxt_second(txt_to_currency.getText().toString());
        model.setTxt_num1(etxt_currency_from.getText().toString());
        model.setTxt_num2(etxt_currency_to.getText().toString());
        adapter.addRecent(model);
    }

    private void reverse(){
        String from = txt_from_currency.getText().toString();
        String to = txt_to_currency.getText().toString();

        txt_from_currency.setText(to);
        txt_to_currency.setText(from);
        int lastIndex1 = index1;
        index1 = index2;
        index2 = lastIndex1;
        calculate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_currency_from:
                SearchActivity.getInstance(this,v.getId(),currencyNames);
                break;
            case R.id.txt_to_currency:
                SearchActivity.getInstance(this,v.getId(),currencyNames);
                break;
            case R.id.img_currency_reverse:
                reverse();
                break;
            case R.id.img_pin_currency:
                addToRecylerView();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SearchActivity.SEARCH_ACTIVITY_REQUEST){
            if (resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                int index = extras.getInt("selectedIndex");
                if (extras.getInt("sender_id") == txt_from_currency.getId()){
                    txt_from_currency.setText(currencyNames[index]);
                    index1 = index;
                }
                else {
                    txt_to_currency.setText(currencyNames[index]);
                    index2 = index;
                }
                calculate();
            }
        }
    }

    /////////////////// Download the currencies

    private void dowloadCurrencies(){
        DownloadXml xmlDownoaded = new DownloadXml(this);
        xmlDownoaded.execute(URL_TCMB);
    }

    private class DownloadXml extends AsyncTask<String,Void,Void> {

        Context mContext;
        ProgressDialog mProgress;

        public DownloadXml(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgress = new ProgressDialog(mContext);
            mProgress.setMessage("Lütfen bekleyin...");
            mProgress.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            InputStream in = null;

            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                in = new BufferedInputStream(conn.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                parse(in);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (in != null)
                        in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            mProgress.dismiss();

            fillLayouts();
        }
    }

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            readFeed(parser);
        } finally {
            in.close();
        }
    }

    private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        currencyList = new ArrayList();
        updatedDate = null;

        parser.require(XmlPullParser.START_TAG, ns, "Tarih_Date");
        updatedDate = parser.getAttributeValue(ns,"Date");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("Currency")) {
                currencyList.add(readCurrency(parser));
            } else {
                skip(parser);
            }
        }
    }

    private CurrencyFeed readCurrency(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Currency");
        String currecy_code = null;
        String currency_name = null;
        String currency_rate = null;

        currecy_code = parser.getAttributeValue(ns,"CurrencyCode");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("CurrencyName")) {
                currency_name = readCurrencyName(parser);
            } else if (name.equals("ForexBuying")) {
                currency_rate = readCurrencyRate(parser);
            } else {
                skip(parser);
            }
        }
        return new CurrencyFeed(currecy_code,currency_name,currency_rate);
    }

    private String readCurrencyName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "CurrencyName");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "CurrencyName");
        return name;
    }

    private String readCurrencyRate(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "ForexBuying");
        String rate = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ForexBuying");
        return rate;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
