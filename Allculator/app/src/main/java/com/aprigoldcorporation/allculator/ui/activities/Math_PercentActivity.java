package com.aprigoldcorporation.allculator.ui.activities;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

public class Math_PercentActivity extends AppCompatActivity {

    int percentType = -1;

    EditText etxt_A, etxt_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_percent);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.math_percent_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rBtn_1)
                    percentType = 0;
                else if (checkedId == R.id.rBtn_2)
                    percentType = 1;
                else
                    percentType = 2;
            }
        });

        radioGroup.check(R.id.rBtn_1);

        etxt_A = (EditText) findViewById(R.id.ET_A_sayisi);
        etxt_B = (EditText) findViewById(R.id.ET_B_sayisi);

        final TextView txt_result = (TextView) findViewById(R.id.txt_math_percent_result);

        Button btn_percent_calc = (Button) findViewById(R.id.btn_percent_calc);
        btn_percent_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (percentType == -1)
                    return;

                double a = Double.valueOf(etxt_A.getText().toString());
                double b = Double.valueOf(etxt_B.getText().toString());
                double result;

                if (percentType == 0)
                    result = a * b / 100.0;
                else if (percentType == 1)
                    result = a * 100.0 / b;
                else
                    result = (b - a) / a * 100.0;

                txt_result.setText(App.doubleFormat(2,result));
            }
        });
    }
}
