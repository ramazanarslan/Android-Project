package com.aprigoldcorporation.allculator.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aprigoldcorporation.allculator.App;
import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.util.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Exam_YgsLysActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnYgs, btnLys, btnYgsLysHesapla;
    RelativeLayout rlYgsInput, rlLysInput;
    AppCompatSpinner katsayi_yil;
    TextView tv_sonuc;
    EditText et_diploma_puani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_ygs_lys);

        initializeViews();

        String[] katsayi_array = getResources().getStringArray(R.array.ygs_lys_katsayi_yillar);
        ArrayAdapter<String> katsayi_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,katsayi_array);
        katsayi_yil.setAdapter(katsayi_adapter);
        katsayi_yil.setSelection(0);

        btnYgsLysHesapla.setOnClickListener(this);
    }

    private void initializeViews() {
        btnLys = (Button) findViewById(R.id.btn_lys);
        btnYgs = (Button) findViewById(R.id.btn_ygs);
        rlLysInput = (RelativeLayout) findViewById(R.id.RL_lys_hesaplamalari);
        rlYgsInput = (RelativeLayout) findViewById(R.id.RL_ygs_hesaplamalari);
        katsayi_yil = (AppCompatSpinner) findViewById(R.id.ygs_lys_katsayi_yil);
        btnYgsLysHesapla = (Button) findViewById(R.id.btn_ygs_lys_hesapla);
        tv_sonuc = (TextView) findViewById(R.id.ygs_lys_sonuc);
        et_diploma_puani = (EditText) findViewById(R.id.et_ygs_diplomapuani);
    }

    public void lysBtnClicked(View v) {
        btnLys.setTextColor(getResources().getColor(R.color.colorSmokeWhite));
        btnLys.setBackgroundColor(getResources().getColor(R.color.colorLogoColor));

        btnYgs.setTextColor(getResources().getColor(android.R.color.black));
        btnYgs.setBackground(getResources().getDrawable(R.drawable.button_checked));

        et_diploma_puani.setText("");
        tv_sonuc.setText("");

        rlLysInput.setVisibility(View.VISIBLE);
        rlYgsInput.setVisibility(View.GONE);
    }

    public void ygsBtnClicked(View v) {
        btnYgs.setTextColor(getResources().getColor(R.color.colorSmokeWhite));
        btnYgs.setBackgroundColor(getResources().getColor(R.color.colorLogoColor));

        btnLys.setTextColor(getResources().getColor(android.R.color.black));
        btnLys.setBackground(getResources().getDrawable(R.drawable.button_checked));

        et_diploma_puani.setText("");
        tv_sonuc.setText("");

        rlLysInput.setVisibility(View.GONE);
        rlYgsInput.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ygs_lys_hesapla:
                Utils.hideKeyboard(this);
                if (rlYgsInput.getVisibility() == View.VISIBLE)
                    ygsHesapla();
                else
                    lysHesapla();
                break;
        }
    }

    private void ygsHesapla() {
        int turk_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_dogru)).getText().toString());
        int turk_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_yanlis)).getText().toString());
        int sosyal_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_dogru)).getText().toString());
        int sosyal_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_yanlis)).getText().toString());
        int mat_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_dogru)).getText().toString());
        int mat_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_yanlis)).getText().toString());
        int fen_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_dogru)).getText().toString());
        int fen_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.ygs_turkce_yanlis)).getText().toString());

        double diploma_puan = Double.valueOf(et_diploma_puani.getText().toString());

        double turk_net = turk_dogru - turk_yanlis / 4.0;
        double sosyal_net = sosyal_dogru - sosyal_yanlis / 4.0;
        double mat_net = mat_dogru - mat_yanlis / 4.0;
        double fen_net = fen_dogru - fen_yanlis / 4.0;

        ((TextView) findViewById(R.id.ygs_turkce_net)).setText(turk_net +"");
        ((TextView) findViewById(R.id.ygs_sosyal_net)).setText(sosyal_net + "");
        ((TextView) findViewById(R.id.ygs_mat_net)).setText(mat_net + "");
        ((TextView) findViewById(R.id.ygs_fen_net)).setText(fen_net + "");

        double ygs_1_puan = (turk_net * 2.131 + sosyal_net * 1.217 + mat_net * 3.665 + fen_net * 3.028) + 98.326;
        ygs_1_puan += (diploma_puan * 5) * 0.12;
        double ygs_2_puan = (turk_net * 2.112 + sosyal_net * 1.206 + mat_net * 2.724 + fen_net * 4.000) + 98.341;
        ygs_2_puan += (diploma_puan * 5) * 0.12;
        double ygs_3_puan = (turk_net * 4.000 + sosyal_net * 3.427 + mat_net * 1.720 + fen_net * 0.947) + 96.287;
        ygs_3_puan += (diploma_puan * 5) * 0.12;
        double ygs_4_puan = (turk_net * 2.958 + sosyal_net * 4.505 + mat_net * 1.696 + fen_net * 0.934) + 96.268;
        ygs_4_puan += (diploma_puan * 5) * 0.12;
        double ygs_5_puan = (turk_net * 3.825 + sosyal_net * 2.356 + mat_net * 2.919 + fen_net * 0.977) + 96.909;
        ygs_5_puan += (diploma_puan * 5) * 0.12;
        double ygs_6_puan = (turk_net * 3.479 + sosyal_net * 1.207 + mat_net * 3.370 + fen_net * 2.002) + 97.657;
        ygs_6_puan += (diploma_puan * 5) * 0.12;

        tv_sonuc.setText("Y-YGS1 Puanı: " + App.doubleFormat(5,ygs_1_puan) +
                        "\nY-YGS2 Puanı: " + App.doubleFormat(5,ygs_2_puan) +
                        "\nY-YGS3 Puanı: " + App.doubleFormat(5,ygs_3_puan) +
                        "\nY-YGS4 Puanı: " + App.doubleFormat(5,ygs_4_puan) +
                        "\nY-YGS5 Puanı: " + App.doubleFormat(5,ygs_5_puan) +
                        "\nY-YGS6 Puanı: " + App.doubleFormat(5,ygs_6_puan));
    }

    private void lysHesapla() {
        int ygs_turkce_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_turkce_dogru)).getText().toString());
        int ygs_turkce_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_turkce_yanlis)).getText().toString());
        int ygs_sos_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_sos_dogru)).getText().toString());
        int ygs_sos_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_sos_yanlis)).getText().toString());
        int ygs_mat_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_mat_dogru)).getText().toString());
        int ygs_mat_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_mat_yanlis)).getText().toString());
        int ygs_fen_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_fen_dogru)).getText().toString());
        int ygs_fen_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_ygs_fen_yanlis)).getText().toString());
        int mat_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_mat_dogru)).getText().toString());
        int mat_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_mat_yanlis)).getText().toString());
        int geo_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_geo_dogru)).getText().toString());
        int geo_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_geo_yanlis)).getText().toString());
        int fizik_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_fizik_dogru)).getText().toString());
        int fizik_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_fizik_yanlis)).getText().toString());
        int kimya_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_kimya_dogru)).getText().toString());
        int kimya_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_kimya_yanlis)).getText().toString());
        int bio_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_bio_dogru)).getText().toString());
        int bio_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_bio_yanlis)).getText().toString());
        int edebiyat_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_edebiyat_dogru)).getText().toString());
        int edebiyat_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_edebiyat_yanlis)).getText().toString());
        int cog_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_cog_dogru)).getText().toString());
        int cog_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_cog_yanlis)).getText().toString());
        int tarih_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_tarih_dogru)).getText().toString());
        int tarih_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_tarih_yanlis)).getText().toString());
        int cog2_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_cog2_dogru)).getText().toString());
        int cog2_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_cog2_yanlis)).getText().toString());
        int felsefe_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_felsefe_dogru)).getText().toString());
        int felsefe_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_felsefe_yanlis)).getText().toString());
        int dil_dogru = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_dil_dogru)).getText().toString());
        int dil_yanlis = Integer.valueOf(((MaterialEditText) findViewById(R.id.lys_dil_yanlis)).getText().toString());

        double diploma_puan = Double.valueOf(et_diploma_puani.getText().toString());

        int ygs_turkce_net = ygs_turkce_dogru - ygs_turkce_yanlis / 4;
        int ygs_sos_net = ygs_sos_dogru - ygs_sos_yanlis / 4;
        int ygs_mat_net = ygs_mat_dogru - ygs_mat_yanlis / 4;
        int ygs_fen_net = ygs_fen_dogru - ygs_fen_yanlis / 4;
        int mat_net = mat_dogru - mat_yanlis / 4;
        int geo_net = geo_dogru - geo_yanlis / 4;
        int fizik_net = fizik_dogru - fizik_yanlis / 4;
        int kimya_net = kimya_dogru - kimya_yanlis / 4;
        int bio_net = bio_dogru - bio_yanlis / 4;
        int edebiyat_net = edebiyat_dogru - edebiyat_yanlis / 4;
        int cog_net = cog_dogru - cog_yanlis / 4;
        int tarih_net = tarih_dogru - tarih_yanlis / 4;
        int cog2_net = cog2_dogru - cog2_yanlis / 4;
        int felsefe_net = felsefe_dogru - felsefe_yanlis / 4;
        int dil_net = dil_dogru - dil_yanlis / 4;

        ((TextView) findViewById(R.id.lys_ygs_turkce_net)).setText(ygs_turkce_net +"");
        ((TextView) findViewById(R.id.lys_ygs_sos_net)).setText(ygs_sos_net + "");
        ((TextView) findViewById(R.id.lys_ygs_mat_net)).setText(ygs_mat_net + "");
        ((TextView) findViewById(R.id.lys_ygs_fen_net)).setText(ygs_fen_net + "");
        ((TextView) findViewById(R.id.lys_mat_net)).setText(mat_net +"");
        ((TextView) findViewById(R.id.lys_geo_net)).setText(geo_net + "");
        ((TextView) findViewById(R.id.lys_fizik_net)).setText(fizik_net + "");
        ((TextView) findViewById(R.id.lys_kimya_net)).setText(kimya_net + "");
        ((TextView) findViewById(R.id.lys_bio_net)).setText(bio_net + "");
        ((TextView) findViewById(R.id.lys_edebiyat_net)).setText(edebiyat_net+ "");
        ((TextView) findViewById(R.id.lys_cog_net)).setText(cog_net + "");
        ((TextView) findViewById(R.id.lys_tarih_net)).setText(tarih_net + "");
        ((TextView) findViewById(R.id.lys_cog2_net)).setText(cog2_net + "");
        ((TextView) findViewById(R.id.lys_felsefe_net)).setText(felsefe_net + "");
        ((TextView) findViewById(R.id.lys_dil_net)).setText(dil_net + "");

        double ygs_1_puan = (ygs_turkce_net * 2.131 + ygs_sos_net * 1.217 + ygs_mat_net * 3.665 + ygs_fen_net * 3.028) + 98.326;
        ygs_1_puan += (diploma_puan * 5) * 0.12;
        double ygs_2_puan = (ygs_turkce_net * 2.112 + ygs_sos_net * 1.206 + ygs_mat_net * 2.724 + ygs_fen_net * 4.000) + 98.341;
        ygs_2_puan += (diploma_puan * 5) * 0.12;
        double ygs_3_puan = (ygs_turkce_net * 4.000 + ygs_sos_net * 3.427 + ygs_mat_net * 1.720 + ygs_fen_net * 0.947) + 96.287;
        ygs_3_puan += (diploma_puan * 5) * 0.12;
        double ygs_4_puan = (ygs_turkce_net * 2.958 + ygs_sos_net * 4.505 + ygs_mat_net * 1.696 + ygs_fen_net * 0.934) + 96.268;
        ygs_4_puan += (diploma_puan * 5) * 0.12;
        double ygs_5_puan = (ygs_turkce_net * 3.825 + ygs_sos_net * 2.356 + ygs_mat_net * 2.919 + ygs_fen_net * 0.977) + 96.909;
        ygs_5_puan += (diploma_puan * 5) * 0.12;
        double ygs_6_puan = (ygs_turkce_net * 3.479 + ygs_sos_net * 1.207 + ygs_mat_net * 3.370 + ygs_fen_net * 2.002) + 97.657;
        ygs_6_puan += (diploma_puan * 5) * 0.12;
        double lys_mf1_puan = (ygs_turkce_net * 1.118 + ygs_sos_net * 0.444 + ygs_mat_net * 1.867 + ygs_fen_net * 0.774 + mat_net * 1.838 + fizik_net * 1.630 + kimya_net * 0.652 + bio_net * 0.630) + 97.485;
        lys_mf1_puan += (diploma_puan * 5) * 0.12;
        double lys_mf2_puan = (ygs_turkce_net * 1.126 + ygs_sos_net * 0.447 + ygs_mat_net * 1.287 + ygs_fen_net * 1.272 + mat_net * 1.083 + fizik_net * 2.121 + kimya_net * 1.361 + bio_net * 1.533) + 97.611;
        lys_mf2_puan += (diploma_puan * 5) * 0.12;
        double lys_mf3_puan = (ygs_turkce_net * 1.131 + ygs_sos_net * 0.636 + ygs_mat_net * 1.291 + ygs_fen_net * 1.071 + mat_net * 0.850 + fizik_net * 2.129 + kimya_net * 1.602+ bio_net * 1.910) + 97.627;
        lys_mf3_puan += (diploma_puan * 5) * 0.12;
        double lys_mf4_puan = (ygs_turkce_net * 1.113 + ygs_sos_net * 0.515 + ygs_mat_net * 1.663 + ygs_fen_net * 0.892 + mat_net * 1.538 + fizik_net * 2.096 + kimya_net * 1.020 + bio_net * 0.627) + 97.369;
        lys_mf4_puan += (diploma_puan * 5) * 0.12;
        double lys_tm1_puan = (ygs_turkce_net * 1.378 + ygs_sos_net * 0.418 + ygs_mat_net * 1.759 + ygs_fen_net * 0.461 + mat_net * 1.548 + edebiyat_net * 1.551 + cog_net * 1.298) + 97.553;
        lys_tm1_puan += (diploma_puan * 5) * 0.12;
        double lys_tm2_puan = (ygs_turkce_net * 1.359 + ygs_sos_net * 0.584 + ygs_mat_net * 1.552 + ygs_fen_net * 0.454 + mat_net * 1.308 + edebiyat_net * 1.885 + cog_net * 1.430) + 97.432;
        lys_tm2_puan += (diploma_puan * 5) * 0.12;
        double lys_tm3_puan = (ygs_turkce_net * 1.439 + ygs_sos_net * 0.825 + ygs_mat_net * 1.096 + ygs_fen_net * 0.454 + mat_net * 1.091 + edebiyat_net * 	2.135 + cog_net * 1.807) + 97.255;
        lys_tm3_puan += (diploma_puan * 5) * 0.12;
        double lys_ts1_puan = (ygs_turkce_net * 1.105 + ygs_sos_net * 0.889 + ygs_mat_net * 0.977 + ygs_fen_net * 0.405 + edebiyat_net * 1.142 + cog_net * 1.276 + tarih_net * 1.584 + cog2_net * 1.622 + felsefe_net * 2.511) + 97.640;
        lys_ts1_puan += (diploma_puan * 5) * 0.12;
        double lys_ts2_puan = (ygs_turkce_net * 1.554 + ygs_sos_net * 0.808 + ygs_mat_net * 0.578 + ygs_fen_net * 0.411 + edebiyat_net * 1.930 + cog_net * 0.817 + tarih_net * 1.606 + cog2_net * 1.161 + felsefe_net * 1.697) + 97.051;
        lys_ts2_puan += (diploma_puan * 5) * 0.12;
        double lys_dil1_puan = (ygs_turkce_net * 1.857 + ygs_sos_net * 0.976 + ygs_mat_net * 0.825 + ygs_fen_net * 0.586 + dil_net * 2.914) + 97.127;
        lys_dil1_puan += (diploma_puan * 5) * 0.12;
        double lys_dil2_puan = (ygs_turkce_net * 3.976 + ygs_sos_net * 1.767 + ygs_mat_net * 1.287 + ygs_fen_net * 0.753 + dil_net * 1.152) + 96.553127;
        lys_dil2_puan += (diploma_puan * 5) * 0.12;
        double lys_dil3_puan = (ygs_turkce_net * 5.236 + ygs_sos_net * 1.879 + ygs_mat_net * 0.884 + ygs_fen_net * 0.517 + dil_net * 0.791) + 96.047;
        lys_dil3_puan += (diploma_puan * 5) * 0.12;

        tv_sonuc.setText("YGS1 Puanı: " + App.doubleFormat(5,ygs_1_puan) +
                "\nYGS2 Puanı: " + App.doubleFormat(5,ygs_2_puan) +
                "\nYGS3 Puanı: " + App.doubleFormat(5,ygs_3_puan) +
                "\nYGS4 Puanı: " + App.doubleFormat(5,ygs_4_puan) +
                "\nYGS5 Puanı: " + App.doubleFormat(5,ygs_5_puan) +
                "\nYGS6 Puanı: " + App.doubleFormat(5,ygs_6_puan) +
                "\nMF1 Puanı: " + App.doubleFormat(5,lys_mf1_puan) +
                "\nMF2 Puanı: " + App.doubleFormat(5,lys_mf2_puan) +
                "\nMF3 Puanı: " + App.doubleFormat(5,lys_mf3_puan) +
                "\nMF4 Puanı: " + App.doubleFormat(5,lys_mf4_puan) +
                "\nTM1 Puanı: " + App.doubleFormat(5,lys_tm1_puan) +
                "\nTM2 Puanı: " + App.doubleFormat(5,lys_tm2_puan) +
                "\nTM3 Puanı: " + App.doubleFormat(5,lys_tm3_puan) +
                "\nTS1 Puanı: " + App.doubleFormat(5,lys_ts1_puan) +
                "\nTS2 Puanı: " + App.doubleFormat(5,lys_ts2_puan) +
                "\nDİL1 Puanı: " + App.doubleFormat(5,lys_dil1_puan) +
                "\nDİL2 Puanı: " + App.doubleFormat(5,lys_dil2_puan) +
                "\nDİL3 Puanı: " + App.doubleFormat(5,lys_dil3_puan));
    }

}
