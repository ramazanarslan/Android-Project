package com.aprigoldcorporation.allculator.ui.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Health_IdealWeightActivity extends AppCompatActivity implements View.OnClickListener {

    App.GENDER picked_gender;
    Double height, weight;

    LinearLayout firstPage, secondPage, thirdPage;
    ImageButton imgbtn_picked_gender;
    EditText etxt_heigth, etxt_weight;
    ImageView img_third_picked_gender;
    TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_ideal_weight);

        firstPage = (LinearLayout) findViewById(R.id.LL_IW_First_Page);
        secondPage = (LinearLayout) findViewById(R.id.LL_IW_Second_Page);
        thirdPage = (LinearLayout) findViewById(R.id.LL_IW_Third_Page);

        ImageButton imgbtn_man = (ImageButton) findViewById(R.id.idealWeight_first_gender_man);
        imgbtn_man.setOnClickListener(this);
        ImageButton imgbtn_woman = (ImageButton) findViewById(R.id.idealWeight_first_gender_woman);
        imgbtn_woman.setOnClickListener(this);

        imgbtn_picked_gender = (ImageButton) findViewById(R.id.idealWeight_second_picked_gender);

        Button btn_seconPage = (Button) findViewById(R.id.idealWeight_btn_secondPage);
        btn_seconPage.setOnClickListener(this);

        etxt_heigth = (EditText) findViewById(R.id.idealWeight_etxt_height);
        etxt_weight = (EditText) findViewById(R.id.idealWeight_etxt_weight);

        img_third_picked_gender = (ImageView) findViewById(R.id.idealWeight_third_picked_gender);

        txt_result = (TextView) findViewById(R.id.idealWeight_result_txt);

        idealWeightReset();

    }

    private void idealWeightReset() {
        picked_gender = null;
        height = Double.NaN;
        weight = Double.NaN;

        txt_result.setText("");

        firstPage.setVisibility(View.VISIBLE);
        secondPage.setVisibility(View.GONE);
        thirdPage.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idealWeight_first_gender_man:
                picked_gender = App.GENDER.MAN;
                imgbtn_picked_gender.setImageResource(R.drawable.man);
                img_third_picked_gender.setImageResource(R.drawable.man);
                firstPage.setVisibility(View.GONE);
                secondPage.setVisibility(View.VISIBLE);
                break;
            case R.id.idealWeight_first_gender_woman:
                picked_gender = App.GENDER.WOMAN;
                imgbtn_picked_gender.setImageResource(R.drawable.woman);
                img_third_picked_gender.setImageResource(R.drawable.woman);
                firstPage.setVisibility(View.GONE);
                secondPage.setVisibility(View.VISIBLE);
                break;
            case R.id.idealWeight_btn_secondPage:
                height = Double.valueOf(etxt_heigth.getText().toString());
                weight = Double.valueOf(etxt_weight.getText().toString());
                getIdealWeight();
                secondPage.setVisibility(View.GONE);
                thirdPage.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void getIdealWeight() {
        height /= 100;
        double vucutYuzeyAlani = 0.20247 * Math.pow(height,0.725) * Math.pow(weight,0.425);
        double yagsizVucutAgirligi;
        double idealVucutAgirligi;
        double vucutKitleEndeksi = weight / Math.pow(height,2);
        if (picked_gender == App.GENDER.MAN){
            yagsizVucutAgirligi = (1.1 * weight) - 128 * (Math.pow(weight,2) / Math.pow(height * 100,2));
            idealVucutAgirligi = 50 + 2.3 * ((height * 39.37) - 60);
        }
        else{
            yagsizVucutAgirligi = (1.07 * weight) - 148 * (Math.pow(weight,2) / Math.pow(height * 100,2));
            idealVucutAgirligi = 45.5 + 2.3 * ((height * 39.37) - 60);
        }

        String sonuc;
        if (vucutKitleEndeksi <= 20)
            sonuc = "Kilonuz düşük";
        else if (vucutKitleEndeksi <= 25)
            sonuc = "Kilonuz normal";
        else if (vucutKitleEndeksi <= 30)
            sonuc = "Aşırı kilolusunuz";
        else if (vucutKitleEndeksi <= 40)
            sonuc = "OBEZ";
        else
            sonuc = "AŞIRI OBEZ";

        txt_result.setText("Vucüt Yüzey Alanınız :\n" + App.doubleFormat(2,vucutYuzeyAlani) + " metrekare\n" +
                            "Yağsız Vücut Ağırlığınız :\n" + App.doubleFormat(2,yagsizVucutAgirligi) + " kg\n" +
                            "İdeal kilonuz :\n" + App.doubleFormat(2,idealVucutAgirligi) + " kg\n" +
                            "Vücut kitle İndeksiniz :\n" + App.doubleFormat(2,vucutKitleEndeksi) + " kg/metrekare\n\n" +
                            "Sonuç :\n" + sonuc);
    }
}
