package com.aprigoldcorporation.examtimer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aprigoldcorporation.examtimer.R;
import com.aprigoldcorporation.examtimer.adapter.GridListAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pascalwelsch.holocircularprogressbar.HoloCircularProgressBar;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by sonu on 08/02/17.
 */
public class GridViewFragment extends Fragment implements View.OnClickListener
{

    TextView textViewTab2_2Dk;
    private Context context;
    private GridListAdapter adapter;
    private ArrayList<String> arrayList;
    private Button selectButton;
    TextView textViewTime, textViewEnterMin;
    View viewLine;
    FloatingActionButton fab1, fab2, fab3;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnCancel, btnSave2_3, btnReset;
    ImageButton btnClear, btnDelete, btnBack;
    private boolean isMusicPlay = false;
    RelativeLayout RLTab2_1, RLTab2_2, RLTab2_3;
    CountDownTimer countDownTimer;
    HoloCircularProgressBar pBar;
    int control = 1, kalanLeft, kalanMinLeft, gelenmin;
    MediaPlayer mpSon;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    GridView gridView;
    int mcc, mnc;


    public GridViewFragment()
    {

    }

    public void CancelOrStop()
    {
        countDownTimer.cancel();
        control = 1;
        if (isMusicPlay)
        {
            mpSon.stop();
            mpSon.reset();
            mpSon.release();
            isMusicPlay = false;
        }

        textViewEnterMin.setText(getString(R.string.EnterMinutes));
        textViewEnterMin.setTextColor(getResources().getColor(R.color.colorTransparentLight2));

        RLTab2_1.setVisibility(View.VISIBLE);
        RLTab2_2.setVisibility(View.INVISIBLE);
        RLTab2_3.setVisibility(View.INVISIBLE);


    }

    private void blink()
    {
        final Handler handler = new Handler();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int timeToBlink = 300;    //in milissegunds
                try
                {
                    Thread.sleep(timeToBlink);
                } catch (Exception e)
                {

                }
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        if (textViewTime.getVisibility() == View.VISIBLE)
                        {


                            textViewTime.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            textViewTime.setVisibility(View.VISIBLE);
                        }
                        if (control == 4)
                        {
                            blink();
                        }
                        else textViewTime.setVisibility(View.VISIBLE);


                    }
                });
            }
        }).start();
    }

    private void blinkProgressBar()
    {
        final Handler handler = new Handler();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int timeToBlink = 300;    //in milissegunds
                try
                {
                    Thread.sleep(timeToBlink);
                } catch (Exception e)
                {
                }
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        if (pBar.getVisibility() == View.VISIBLE)
                        {
                            pBar.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            pBar.setVisibility(View.VISIBLE);
                        }
                        if (control == 5)
                        {
                            blinkProgressBar();
                        }
                        else
                        {
                            pBar.setVisibility(View.INVISIBLE);
                        }


                    }
                });
            }
        }).start();
    }

    //comtrol 0 ise null 1 ise ilerle (FAB1), 2 ise start(FAB2) ,3 ise pause, 4 play, 5 stop dur.
    public void fab1Click()
    {
        if ((textViewEnterMin.getText().toString().matches("[0-9]+") && control == 1))
        {
            gelenmin = Integer.valueOf(textViewEnterMin.getText().toString());
            control = 2;
            RLTab2_1.setVisibility(View.INVISIBLE);
            RLTab2_2.setVisibility(View.VISIBLE);
            RLTab2_3.setVisibility(View.INVISIBLE);
            textViewTab2_2Dk.setText(textViewEnterMin.getText().toString() + " " + getString(R.string.Minutes));

        }
    }

    public void fab2Click()
    {


        //comtrol 0 ise null 1 ise ilerle (FAB1), 2 ise start(FAB2) ,3 ise pause, 4 play, 5 stop dur.

        //contol =1 yapıldı  icon ilerle olsun RL1 visible olsun RLT2 inVisible olsun RL2 invisible olmalı başta


        //1 ise (ilerle)

        //İF gelen sayı numara ise  ve control 1 ise ->
        //gelentext=textviewgelenminutes olsun
        //control ü 2 yap
        //RL1 invisible olsun RLT2 Visible olsun RL2 invisible
        //play işareti olsun


        //2 ise (start)
        //İF ses seviyesi ortanın altında ise ses seviyesi düşük duymayabilirsiniz uyarısı versin

        //İF control 2  ve 5 ve 5 in katlarından bir listview ise
        //timer başlar eğer gelenmin -listwievdeki değer ise 1.bildirim ve 2.bildirim
        //ontickin içinde contol3 yanipause edilebilir
        //pause işareti olsun eklene bildirimler checkbox da olsun İF unchecked ise cancel olsun short notification
        //finished eventinde stop işareti çıkar ve alarm çalar ve control 5 olur


        //3 pause ise
        //en sonda kalan saniyeleri kaydet
        ///iptal et timer ı ve controlü 4 playyapılabilr yap
        //play işareti çıksın
        //texttimer ı yanıp sönsün
        //Reset buttonu çıksın visible


        //İF 4 ise (play) ve control 4 ise
        //iptal edilern timer dan kalan saniyeyi kaydededen pause den  alır tekrar timer olarak çalıştır
        //pasuse işareti çıksın ve control 3 olsun

        //finished eventinde stop işareti çıkar ve alarm çalar ve control 5 olur


        //stop da
        //RL1 visible RL2veRL3 inVisible

        // cancel() çalıştır. görünme silinme  control 1 olur.

        //comtrol 0 ise null 1 ise ilerle , 2 ise start ,3 ise pause, 4 play, 5 stop dur.
        //İF control 1 ise ilerle

        //2 ise (start)

        if (control == 2)
        {
            //ses seviyesi ekle // FIXME: 14.06.2017  //ses seviyesi ortanın altında ise ses seviyesi düşük duymayabilirsiniz uyarısı versin

            //7 tane hatılatma eklenebilir olsun.
            //// FIXME: 14.06.2017 Listviewden gelecen hatırlatmalar


            countDownTimer = new CountDownTimer(gelenmin * 60 * 1000, 100)
            {
                int secondsLeft = 0;
                int minLeft;
                String minLeftString;

                public void onTick(long ms)
                {
                    control = 3;
                    if (Math.round((float) ms / 1000.0f) != secondsLeft)
                    {
                        secondsLeft = Math.round((float) ms / 1000.0f);
                        //burda sayıları yuvarlama VAR.


                        String seconleftstring = String.valueOf(secondsLeft);
                        if (secondsLeft >= 60)
                        {
                            seconleftstring = String.valueOf(secondsLeft % 60);
                        }
                        //secondleftstring yazıyor

                        if (seconleftstring.length() == 1)
                        {
                            seconleftstring = "0" + seconleftstring;
                        }
                        //secondleftstring yani sn de tek basamaklıya düşünce 0 ekleme başına

                        minLeft = secondsLeft / 60;
                        minLeftString = String.valueOf(minLeft);
                        //sn yi dk kaya çevirdik

                        if (minLeftString.length() == 1)
                        {
                            minLeftString = "0" + minLeftString;
                        }
                        //dk tek basamaklı da 0 sourunu

                        Float flt = (float) secondsLeft / ((float) gelenmin * 60.0f);
                        pBar.setProgress(flt);
                        //min progresbarı

                        textViewTime.setText(minLeftString + ":" + seconleftstring);

                        kalanLeft = secondsLeft;
                        kalanMinLeft = minLeft;
                    }

                }

                public void onFinish()
                {
                    control = 5;
                    fab3.setImageResource(android.R.drawable.alert_light_frame);
                    btnCancel.setVisibility(View.INVISIBLE);
                    mpSon.start();
                    isMusicPlay = true;

                    //alarm çaldı

                    //finished ve yazacak ve progress bar vsi inv olsun
                    blinkProgressBar();

                    textViewTime.setText(getString(R.string.Finished));
                    btnSave2_3.setVisibility(View.INVISIBLE);


                }
            }.start();
            RLTab2_1.setVisibility(View.INVISIBLE);
            RLTab2_2.setVisibility(View.INVISIBLE);
            RLTab2_3.setVisibility(View.VISIBLE);
        }
        //3 pause ise
        //en sonda kalan saniyeleri kaydet
        ///iptal et timer ı ve controlü 4 playyapılabilr yap
        //play işareti çıksın
        //texttimer ı yanıp sönsün
        //Reset buttonu çıksın visible

    }

    public void fab3Click()
    {
        if (control == 3)
        {

            control = 4;
            countDownTimer.cancel();
            blink();
            fab3.setImageResource(android.R.drawable.ic_media_play);
            btnReset.setVisibility(View.VISIBLE);
        }

        //İF 4 ise (play) ve control 4 ise
        //iptal edilern timer dan kalan saniyeyi kaydededen pause den  alır tekrar timer olarak çalıştır
        //pasuse işareti çıksın ve control 3 olsun

        //finished eventinde stop işareti çıkar ve alarm çalar ve control 5 olur

        else if (control == 4)
        {

            btnReset.setVisibility(View.INVISIBLE);

            textViewTime.setVisibility(View.VISIBLE);

            control = 3;
            fab3.setImageResource(android.R.drawable.ic_media_pause);
            countDownTimer = new CountDownTimer(kalanLeft * 1000, 100)
            {

                String minLeftString;

                public void onTick(long ms)
                {
                    if (Math.round((float) ms / 1000.0f) != kalanLeft)
                    {
                        kalanLeft = Math.round((float) ms / 1000.0f);

                        String seconleftstring = String.valueOf(kalanLeft);
                        if (kalanLeft >= 60)
                        {
                            seconleftstring = String.valueOf(kalanLeft % 60);
                        }

                        if (seconleftstring.length() == 1)
                        {
                            seconleftstring = "0" + seconleftstring;
                        }

                        kalanMinLeft = kalanLeft / 60;
                        minLeftString = String.valueOf(kalanMinLeft);

                        if (minLeftString.length() == 1)
                        {
                            minLeftString = "0" + minLeftString;
                        }

                        Float flt = (float) kalanLeft / ((float) gelenmin * 60.0f);

                        pBar.setProgress(flt);

                        textViewTime.setText(minLeftString + ":" + seconleftstring);
                    }

                }

                public void onFinish()
                {
                    control = 5;
                    fab3.setImageResource(android.R.drawable.alert_light_frame);
                    btnCancel.setVisibility(View.INVISIBLE);
                    mpSon.start();
                    isMusicPlay = true;
                    blinkProgressBar();
                    textViewTime.setText(getString(R.string.Finished));
                    btnSave2_3.setVisibility(View.INVISIBLE);


                }
            }.start();

        }
        //stop da

        else if (control == 5)
        {
            CancelOrStop();

        }
    }

    public void SetOnClickListenerlar()
    {


        btnReset.setOnClickListener(this);
        btnSave2_3.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.grid_view_fragment, container, false);
    }

    public void FindViewler(View view)
    {
        // btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);
        textViewTab2_2Dk = (TextView) view.findViewById(R.id.textViewtab2DK);
        textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewEnterMin = (TextView) view.findViewById(R.id.textViewEnterMin);
        btn0 = (Button) view.findViewById(R.id.btn0);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        btn9 = (Button) view.findViewById(R.id.btn9);
        btnClear = (ImageButton) view.findViewById(R.id.btnClear);
        btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
        btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSave2_3 = (Button) view.findViewById(R.id.btnSave2_3);
        btnReset = (Button) view.findViewById(R.id.btnReset);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);
        RLTab2_1 = (RelativeLayout) view.findViewById(R.id.RLTab2_1);
        RLTab2_2 = (RelativeLayout) view.findViewById(R.id.RLTab2_2);
        RLTab2_3 = (RelativeLayout) view.findViewById(R.id.RLTab2_3);
        pBar = (HoloCircularProgressBar) view.findViewById(R.id.holoCircularProgressBar);
        viewLine = view.findViewById(R.id.viewLine);

    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        // selectButton = (Button) view.findViewById(R.id.select_button);
        loadGridView(view);
//        onClickEvent(view);

        FindViewler(view);
        SetOnClickListenerlar();
        mpSon = MediaPlayer.create(getContext(), R.raw.beep);
        mpSon.setLooping(true);



        textViewEnterMin.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                textViewEnterMin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            @Override
            public void afterTextChanged(Editable s)
            {


                if (textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    viewLine.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

                    if (textViewEnterMin.getText().length() > 3)
                    {

                        Toast.makeText(getActivity(), getString(R.string.Please), Toast.LENGTH_SHORT).show();
                        String etextd = textViewEnterMin.getText().toString();
                        etextd = etextd.substring(0, etextd.length() - 1);
                        textViewEnterMin.setText(etextd);


                    }
                    fab1.setEnabled(true);

                }
                else
                {
                    fab1.setEnabled(false);
                    viewLine.setBackgroundColor(Color.parseColor("#edfcfafa"));

                }
            }
        });


    }


    public void onClick(View v)
    {

        switch (v.getId())
        {

            case R.id.btn0:
                if (textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    String etext = textViewEnterMin.getText().toString();
                    textViewEnterMin.setText(etext + "0");
                }

                break;
            case R.id.btn1:
             //   Toast.makeText(context, Locale.getDefault().getDisplayLanguage(), Toast.LENGTH_SHORT).show();

                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext1 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext1 + "1");
                break;
            case R.id.btn2:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext2 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext2 + "2");
                break;
            case R.id.btn3:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext3 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext3 + "3");
                break;
            case R.id.btn4:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext4 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext4 + "4");
                break;
            case R.id.btn5:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext5 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext5 + "5");
                break;
            case R.id.btn6:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext6 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext6 + "6");
                break;
            case R.id.btn7:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext7 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext7 + "7");
                break;
            case R.id.btn8:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }
                String etext8 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext8 + "8");
                break;
            case R.id.btn9:
                if (!textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.WHITE);
                    textViewEnterMin.setText("");
                }

                String etext9 = textViewEnterMin.getText().toString();
                textViewEnterMin.setText(etext9 + "9");
                break;
            case R.id.btnClear:
                if (textViewEnterMin.getText().toString().matches("[0-9]+"))
                {
                    textViewEnterMin.setTextColor(Color.parseColor("#8a000000"));
                    textViewEnterMin.setText(getString(R.string.EnterMinutes));
                    textViewEnterMin.setTextColor(getResources().getColor(R.color.colorTransparentLight2));


                    viewLine.setBackgroundColor(Color.parseColor("#edfcfafa"));
                }

                break;
            case R.id.btnDelete:
                String etextd = textViewEnterMin.getText().toString();
                if (textViewEnterMin.getText().toString().matches(""))
                {
                    textViewEnterMin.setTextColor(Color.parseColor("#8a000000"));
                    textViewEnterMin.setText(getString(R.string.EnterMinutes));
                    textViewEnterMin.setTextColor(getResources().getColor(R.color.colorTransparentLight2));

                    viewLine.setBackgroundColor(Color.parseColor("#edfcfafa"));

                }
                else
                {
                    if (textViewEnterMin.getText().toString().matches("[0-9]+"))
                    {
                        etextd = etextd.substring(0, etextd.length() - 1);
                        if (etextd.matches(""))
                        {
                            textViewEnterMin.setTextColor(Color.parseColor("#8a000000"));
                            textViewEnterMin.setText(getString(R.string.EnterMinutes));
                            textViewEnterMin.setTextColor(getResources().getColor(R.color.colorTransparentLight2));
                        }
                        else
                            textViewEnterMin.setText(etextd);
                    }
                }

                break;


            case R.id.btnCancel:
                CancelOrStop();
                break;


            case R.id.btnBack:
                gelenmin = 0;
                control = 1;
                RLTab2_1.setVisibility(View.VISIBLE);
                RLTab2_2.setVisibility(View.INVISIBLE);
                RLTab2_3.setVisibility(View.INVISIBLE);
                textViewTab2_2Dk.setText("");
                textViewEnterMin.setText(getString(R.string.EnterMinutes));
                textViewEnterMin.setTextColor(getResources().getColor(R.color.colorTransparentLight2));


            case R.id.fab1:


                fab1Click();
                break;

            case R.id.fab2:
                fab2Click();
                break;

            case R.id.fab3:
                fab3Click();
                break;
            case R.id.btnReset:
                control = 2;
                countDownTimer.cancel();
                fab2Click();

                break;
            case R.id.btnSave2_3:
                final int gelendk = 120, item1 = 30, item2 = 5, item3 = 0, item4 = 0;
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View view2 = getLayoutInflater(null).inflate(R.layout.save_dialog, null);
                final EditText editTextSavedGet = (EditText) view2.findViewById(R.id.editTextSavedGed);
                mBuilder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {




                        ((MainActivity) getActivity()).TabDegistir(0, editTextSavedGet.getText().toString(),gelendk,item1,item2,item3,item4);
//                        AddToSave(editTextSavedGet.getText().toString());
                    }
                });
                mBuilder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(view2);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();

               /* String array0 = arrayList.get(0);
                String array1 = arrayList.get(1);
                String array2 = arrayList.get(2);
                String array3 = arrayList.get(3);*/

//arrayden al önce başını kes sonra sonunnu kes sayıyı al
//                0 olmayanları al, stringi "" olmayanları al chechbox ı true alanları al ve bunları countdown timera gönder ve
// Bunları save e basmadan önce float action buttonın playindenn önce yap.
//                sonra save de bu sayıları sadece al ve alert dialogdan gelen metni yansıt
//                saherederefernecs da etiket ismi . s0 dışında sayı olanları al . bunları listviewwe ekle ve edit tuşu ekle
//                eğer edit e basarlarsa tab1 de  ve sayıları değiştirilerse veyahut silerlerse shared refences daki değeri değiştir
//                saved deki bir listview deki iteme tıklayınca veyahut bunu başlat olursa
//                main acivity de shared references ı kontol et ve main activy de sonra tab1 de playe basılınca sharedreference değişsin ve o değer tab2 getcurrent items yapsın ve
// shared refencesdan ekle
//array lerin sayı olan yerini al
                //label al
//                bunları aklında kaydet de sharedReferecnecese ekle

                break;

            default:
                break;
        }

    }

    private void loadGridView(View view)
    {

        gridView = (GridView) view.findViewById(R.id.grid_view);
        arrayList = new ArrayList<>();

            arrayList.add(getString(R.string.Remember) + "\n" + " " + getString(R.string.Last) + " " + "30" + " " + getString(R.string.Min));
            arrayList.add(getString(R.string.Remember) + "\n" + " " + getString(R.string.Last) + " " + "5" + " " + getString(R.string.Min));
            arrayList.add("");
            arrayList.add("");

        adapter = new GridListAdapter(context, arrayList);
        gridView.setAdapter(adapter);


    }
}
