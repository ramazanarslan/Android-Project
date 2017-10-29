package com.aprigoldcorporation.allculator.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.util.Utils;

public class Exam_OBPActivity extends AppCompatActivity {

    EditText et_obp_number;
    CheckBox checkBox_prev_yerlestirme;
    TextView txt_obp_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_obp);

        et_obp_number = (EditText) findViewById(R.id.ET_obp_number);
        checkBox_prev_yerlestirme = (CheckBox) findViewById(R.id.checkbox_prev_yerlestirme);
        txt_obp_result = (TextView) findViewById(R.id.TV_obp_result);

        Button btn_obp_hesaplama = (Button) findViewById(R.id.btn_yds_hesapla);
        btn_obp_hesaplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obpHesapla();
            }
        });

    }

    private void obpHesapla() {
        String numberStr = et_obp_number.getText().toString();
        if (Utils.isNumeric(numberStr)){
            double number = Double.valueOf(numberStr);
            if (number < 50.0)
                number = 50.0;
            else if (number > 100.0)
                Toast.makeText(getApplicationContext(),"Puan 100'den büyük olamaz",Toast.LENGTH_SHORT);

            double result = number * 5.0;
            if (!checkBox_prev_yerlestirme.isChecked()){
                txt_obp_result.setText("Orta Öğretim Başarı Puanınız (OBP) : " + App.doubleFormat(2,result) +
                                        "\n0.12 katsayılı puan : " + App.doubleFormat(2,result * 0.12) +
                                        "\n0.18 katsayılı puan : " + App.doubleFormat(2,result * 0.18) + " (Ek Puanlı)");
            }
            else {
                txt_obp_result.setText("Orta Öğretim Başarı Puanınız (OBP) : " + App.doubleFormat(2,result) +
                        "\n0.06 katsayılı puan : " + App.doubleFormat(2,result * 0.06) +
                        "\n0.09 katsayılı puan : " + App.doubleFormat(2,result * 0.09) + " (Ek Puanlı)");
            }

        }
    }
}
