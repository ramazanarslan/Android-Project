package com.aprigoldcorporation.allculator.ui.activities;


import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

/**
 * Created by RamazanArslan on 8.09.2017.
 */

public class Math_Profit_LossActivity extends AppCompatActivity implements VerticalStepperForm
{
    private VerticalStepperFormLayout verticalStepperForm;
    RadioRealButton btn_kar, btn_zarar;
    RadioRealButtonGroup group;
    EditText et_maliyettutari;
    RadioRealButtonGroup group_hesaplama;
    RadioRealButton btn_kar_zarar_oranı_hesaplama;
    RadioRealButton btn_maliyet_tutarı_hesaplama;
    RadioRealButton btn_gelir_tutarı_hesaplama;
    EditText et_kar_zarar_orani;
    protected AppCompatButton confirmationButton;
    String[] mySteps = {"Kar Zarar Hesaplama", "Hesaplama Şekli", "Maliyet Tutarı", "Kar Oranı"};


    TextView txt_profit_loss_result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_profit_loss);


        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();


        btn_kar = (RadioRealButton) findViewById(R.id.btn_kar);
        btn_zarar = (RadioRealButton) findViewById(R.id.btn_zarar);
        group = (RadioRealButtonGroup) findViewById(R.id.btn_group);


        btn_gelir_tutarı_hesaplama = (RadioRealButton) findViewById(R.id.btn_gelir_tutarı_hesaplama);
        btn_maliyet_tutarı_hesaplama = (RadioRealButton) findViewById(R.id.btn_maliyet_tutarı_hesaplama);
        btn_kar_zarar_oranı_hesaplama = (RadioRealButton) findViewById(R.id.btn_kar_zarar_oranı_hesaplama);
        group_hesaplama = (RadioRealButtonGroup) findViewById(R.id.btn_group_hesaplama_tipi);

        group.setPosition(0);
        group_hesaplama.setPosition(0);

        confirmationButton = (AppCompatButton) findViewById(ernestoyaquello.com.verticalstepperform.R.id.next_step);

        confirmationButton.setText("ilerle");

        et_maliyettutari.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {


            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (et_maliyettutari.length() >= 1 && et_maliyettutari.length() <= 15)
                {
                    if (et_maliyettutari.getText().toString().matches("\\d+(?:\\.\\d+)?"))
                    {
                        verticalStepperForm.setActiveStepAsCompleted();

                    }
                    else
                    {
                        String errorMessage = "Lütfen bir sayi giriniz";
                        verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                    }

                }

                else
                {
                    String errorMessage = "Lütfen bir sayi giriniz";
                    verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                }
            }
        });
        et_kar_zarar_orani.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

                if (et_kar_zarar_orani.length() >= 1 && et_kar_zarar_orani.length() <= 15)
                {
                    if (et_kar_zarar_orani.getText().toString().matches("\\d+(?:\\.\\d+)?"))
                    {
                        verticalStepperForm.setActiveStepAsCompleted();

                    }
                    else
                    {
                        String errorMessage = "Lütfen bir sayi giriniz";
                        verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                    }
                }
                else
                {
                    String errorMessage = "Lütfen bir sayi giriniz";
                    verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                }
            }
        });

        txt_profit_loss_result = (TextView) findViewById(R.id.math_profit_loss_result);
    }

    @Override
    public View createStepContentView(int stepNumber)
    {

        View view = null;
        switch (stepNumber)
        {
            case 0:
                view = createProfitOrLoss();
                break;
            case 1:
                view = createTypeofCallculateStep();
                break;
            case 2:
                view = createMaliyetTurari();
                break;
            case 3:
                view = createKarOrani();
                break;
        }
        return view;

    }

    private View createProfitOrLoss()
    {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout profit_or_loss_layout = (LinearLayout) inflater.inflate(R.layout.profitorloss_step_layout, null, false);

        return profit_or_loss_layout;
    }

    private View createTypeofCallculateStep()
    {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout TypeofCallculate = (LinearLayout) inflater.inflate(R.layout.type_of_calculate_step_layout, null, false);


        return TypeofCallculate;


    }

    private View createMaliyetTurari()
    {
        et_maliyettutari = new EditText(this);
        et_maliyettutari.setSingleLine(true);
        et_maliyettutari.setRawInputType(InputType.TYPE_CLASS_NUMBER);


        return et_maliyettutari;
    }

    private View createKarOrani()
    {
        et_kar_zarar_orani = new EditText(this);
        et_kar_zarar_orani.setSingleLine(true);
        et_kar_zarar_orani.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        return et_kar_zarar_orani;
    }

    @Override
    public void onStepOpening(int stepNumber)
    {

        switch (stepNumber)
        {
            case 0:
                checkProfitorLoss();
                break;
            case 1:
                checkHesaplaSekli();
                break;
            case 2:
                checkMaliyetTurari();
                break;
            case 3:
                checkKarOrani();
                break;
        }
    }

    private void checkProfitorLoss()
    {

        verticalStepperForm.setActiveStepAsCompleted();


    }

    private void checkHesaplaSekli()
    {
        confirmationButton.setText("ilerle");

        if (group.getPosition() == 0)
        {
            if (group_hesaplama.getPosition() == 0)
            {

                mySteps[2] = "Maliyet Tutarı";
                mySteps[3] = "Kar Oranı";

            }
            else if (group_hesaplama.getPosition() == 1)
            {
                mySteps[2] = "Geliş(Satış) Tutarı";
                mySteps[3] = "Kar Oranı";

            }
            else if (group_hesaplama.getPosition() == 2)
            {
                mySteps[2] = "Maliyet Tutarı";
                mySteps[3] = "Geliş(Satış) Tutarı";
            }

            btn_kar_zarar_oranı_hesaplama.setText("Kar oranı hesaplama");

        }


        else if (group.getPosition() == 1)
        {
            if (group_hesaplama.getPosition() == 0)
            {
                mySteps[2] = "Maliyet Tutarı";
                mySteps[3] = "Zarar Oranı";

            }
            else if (group_hesaplama.getPosition() == 1)
            {
                mySteps[2] = "Geliş(Satış) Tutarı";
                mySteps[3] = "Zarar Oranı";

            }
            else if (group_hesaplama.getPosition() == 2)
            {
                mySteps[2] = "Maliyet Tutarı";
                mySteps[3] = "Geliş(Satış) Tutarı";

            }


            btn_kar_zarar_oranı_hesaplama.setText("Zarar oranı hesaplama");


        }
        verticalStepperForm.setActiveStepAsCompleted();
    }

    private void checkMaliyetTurari()
    {

        if (et_maliyettutari.getText().toString().matches("\\d+(?:\\.\\d+)?"))
        {
            verticalStepperForm.setActiveStepAsCompleted();

        }
        else
        {
            String errorMessage = "Lütfen bir sayi giriniz";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }


    }

    private void checkKarOrani()
    {

        if (et_maliyettutari.getText().toString().matches("\\d+(?:\\.\\d+)?"))
        {
            verticalStepperForm.setActiveStepAsCompleted();

        }
        else
        {
            String errorMessage = "Lütfen bir sayi giriniz";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }

    }

    @Override
    public void sendData()
    {
        Double maliyetTutar = 0.0;
        Double gelirTutar = 0.0;
        Double brutKar = 0.0;
        Double karOran = 0.0;
        Double karMarj = 0.0;
        Double zararOran = 0.0;
        double num1 = Double.valueOf(et_maliyettutari.getText().toString());
        double num2 = Double.valueOf(et_kar_zarar_orani.getText().toString());
        if (group.getPosition() == 0)
        {
            switch (group_hesaplama.getPosition())
            {
                case 0:
                    maliyetTutar = num1;
                    karOran = num2;
                    brutKar = (num1 * num2 / 100.0);
                    gelirTutar = num1 + brutKar;
                    karMarj = brutKar * 100.0 / gelirTutar;
                    break;
                case 1:
                    gelirTutar = num1;
                    karOran = num2;
                    maliyetTutar = (100.0 * gelirTutar) / (100.0 + karOran);
                    brutKar = maliyetTutar * karOran / 100.0;
                    karMarj = (brutKar * 100.0) / gelirTutar;
                    break;
                case 2:
                    maliyetTutar = num1;
                    gelirTutar = num2;
                    karOran = Math.abs(maliyetTutar - gelirTutar) / maliyetTutar * 100.0;
                    brutKar = maliyetTutar * karOran / 100.0;
                    karMarj = (brutKar * 100.0) / gelirTutar;
                    break;
            }
            txt_profit_loss_result.setText("Maliyet Tutarı : " + App.doubleFormat(1, maliyetTutar) +
                    "\nGelir Tutarı : " + App.doubleFormat(1, gelirTutar) +
                    "\nBrüt Kar Tutarı : " + App.doubleFormat(1, brutKar) +
                    "\nKar Oranı : %" + App.doubleFormat(1, karOran) + " (Brüt kârın maliyet tutarına göre yüzde oranı)" +
                    "\nKar Marjı : %" + App.doubleFormat(3, karMarj) + " (Brüt kârın gelir tutarına göre yüzde oranı)");
        }
        else
        {
            switch (group_hesaplama.getPosition())
            {
                case 0:
                    maliyetTutar = num1;
                    zararOran = num2;
                    brutKar = (num1 * num2 / 100.0);
                    gelirTutar = num1 - brutKar;
                    break;
                case 1:
                    gelirTutar = num1;
                    zararOran = num2;
                    maliyetTutar = (100.0 * gelirTutar) / (100.0 - zararOran);
                    brutKar = maliyetTutar * zararOran / 100.0;
                    break;
                case 2:
                    maliyetTutar = num1;
                    gelirTutar = num2;
                    brutKar = maliyetTutar - gelirTutar;
                    zararOran = (brutKar * 100.0) / maliyetTutar;
                    break;
            }
            txt_profit_loss_result.setText("Maliyet Tutarı : " + App.doubleFormat(1, maliyetTutar) +
                    "\nGelir Tutarı : " + App.doubleFormat(1, gelirTutar) +
                    "\nBrüt Zarar Tutarı : " + App.doubleFormat(1, brutKar) +
                    "\nZarar Oranı : %" + App.doubleFormat(1, zararOran) + " (Brüt zararın maliyet tutarına göre yüzde oranı)");
        }
    }

}
