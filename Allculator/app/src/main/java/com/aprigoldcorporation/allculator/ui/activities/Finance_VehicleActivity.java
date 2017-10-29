package com.aprigoldcorporation.allculator.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

public class Finance_VehicleActivity extends AppCompatActivity {

    int pickedVehicleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_vehicle);

        pickedVehicleGroup = 0;

        String[] sureler = getResources().getStringArray(R.array.arac_muayene_gecikme_sure);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sureler);
        final Spinner spin_gecikme_sure = (Spinner) findViewById(R.id.finance_vehicle_delayTime);
        spin_gecikme_sure.setAdapter(adapter);


        final TextView txt_result = (TextView) findViewById(R.id.finance_vehicle_txt_result);

        Button btn_calculate = (Button) findViewById(R.id.finance_vehicle_btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delayedTime = spin_gecikme_sure.getSelectedItemPosition();
                double muayene_ucret;
                double gecikme_cezasi;
                double total;
                if (pickedVehicleGroup == 0){
                    muayene_ucret = 267.86;
                    gecikme_cezasi = (muayene_ucret * delayedTime) / 100.0;
                    total = muayene_ucret + gecikme_cezasi;
                }
                else if (pickedVehicleGroup == 1){
                    muayene_ucret = 198.24;
                    gecikme_cezasi = (muayene_ucret * delayedTime) / 100.0;
                    total = muayene_ucret + gecikme_cezasi;
                }
                else{
                    muayene_ucret = 101.48;
                    gecikme_cezasi = (muayene_ucret * delayedTime) / 100.0;
                    total = muayene_ucret + gecikme_cezasi;
                }

                txt_result.setText("Araç Muayene Ücreti (2017): " + App.doubleFormat(2,muayene_ucret) + " TL (KDV Dahil)\n" +
                                    "Gecikme Süresi: " + delayedTime + " ay\n" +
                                    "Gecikme Cezası: " + App.doubleFormat(2,gecikme_cezasi) + " TL (KDV Dahil)\n" +
                                    "Ödenecek Toplam Tutar: " + App.doubleFormat(2,total) + " TL (KDV Dahil)");
            }
        });
    }

    public void vehiclePicked(View v){
        if (v.getTag().equals("vehicle1"))
            pickedVehicleGroup = 0;
        else if (v.getTag().equals("vehicle2"))
            pickedVehicleGroup = 1;
        else
            pickedVehicleGroup = 2;
    }
}
