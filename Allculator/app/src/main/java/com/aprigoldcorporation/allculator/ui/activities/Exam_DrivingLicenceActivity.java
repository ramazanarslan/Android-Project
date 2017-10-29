package com.aprigoldcorporation.allculator.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

public class Exam_DrivingLicenceActivity extends AppCompatActivity {

    EditText et_dl_number;
    Button btn_dl_hesapla;
    TextView txt_dl_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_driving_licence);

        et_dl_number = (EditText) findViewById(R.id.ET_dl_number);
        btn_dl_hesapla = (Button) findViewById(R.id.btn_dl_hesapla);
        txt_dl_result = (TextView) findViewById(R.id.TV_dl_result);

        btn_dl_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number = Double.valueOf(et_dl_number.getText().toString());
                double result = number * 2;
                if (result >= 70)
                    txt_dl_result.setText("Ehliyet Sınav Puanı : " + App.doubleFormat(1,result) +
                        "\nEhliyet Sınavı Sonuç : Ehliyet sınavından başarıyla geçtiniz");
                else
                    txt_dl_result.setText("Ehliyet Sınav Puanı : " + App.doubleFormat(1,result) +
                            "\nEhliyet Sınavı Sonuç : Maalesef ehliyet sınavından " + (int)(35 - number) + " soruyla kaldınız");
            }
        });
    }
}
