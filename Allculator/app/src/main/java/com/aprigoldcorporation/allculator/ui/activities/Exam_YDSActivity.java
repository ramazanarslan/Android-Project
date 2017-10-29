package com.aprigoldcorporation.allculator.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

public class Exam_YDSActivity extends AppCompatActivity {

    EditText et_yds_number;
    Button btn_yds_hesapla;
    TextView txt_yds_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_yds);

        et_yds_number = (EditText) findViewById(R.id.ET_yds_number);
        btn_yds_hesapla = (Button) findViewById(R.id.btn_yds_hesapla);
        txt_yds_result = (TextView) findViewById(R.id.TV_yds_result);

        btn_yds_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number = Double.valueOf(et_yds_number.getText().toString());
                double result = number * 1.25;
                txt_yds_result.setText("YDS Net : " + App.doubleFormat(1,number) +
                                        "\nYDS Puan : " + App.doubleFormat(2,result));
            }
        });
    }
}
