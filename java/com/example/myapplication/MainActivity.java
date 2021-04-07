package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText isimDuzenleme, emailDuzenleme, sifreDuzenleme, sifreDuzenlemeTekrari;
    private Button resimButonu, kaydolButonu;
    private TextView isimDuzenlemeUyarisi, emailDuzenlemeUyarisi, sifreDuzenlemeUyarisi, sifreDuzenlemeTekrariUyarisi;
    private Spinner spinnerUlke;
    private RadioGroup rgCinsiyet;
    private CheckBox onayKutusu;
    private Constraints parent;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        resimButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Resim Yüklemeniz Gerekiyor", Toast.LENGTH_SHORT).show();
            }
        });

        kaydolButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initRegister(){
        Log.d(TAG, "initRegister: Started");

        if(validateData()){
            if (onayKutusu.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(this, "Onay Metnini Onaylamanız Gerekiyor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");
        isimDuzenlemeUyarisi.setVisibility(View.GONE);
        emailDuzenlemeUyarisi.setVisibility(View.GONE);
        sifreDuzenlemeUyarisi.setVisibility(View.GONE);
        sifreDuzenlemeTekrariUyarisi.setVisibility(View.GONE);

        String isim = isimDuzenleme.getText().toString();
        String email = emailDuzenleme.getText().toString();
        String ulke = spinnerUlke.getSelectedItem().toString();
        String cinsiyet = "";
        switch (rgCinsiyet.getCheckedRadioButtonId()){
            case R.id.rbKadin:
                cinsiyet = "Kadın";
            case R.id.rbErkek:
                cinsiyet = "Erkek";
                break;
            case R.id.rbDiger:
                cinsiyet = "Diğer";
                break;
            default:
                cinsiyet = "Bilinmeyen";
                break;
        }

        String snackText = "İsim: " + isim + "\n" +
                "Email: " + email + "\n" +
                "Cinsiyet" + cinsiyet + "\n" +
                "Ülke" + ulke;

        Log.d(TAG, "showSnackBar: Snack Bar Text: " +snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Yoksay", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isimDuzenleme.setText("");
                        emailDuzenleme.setText("");
                        sifreDuzenleme.setText("");
                        sifreDuzenlemeTekrari.setText("");
                    }
                }).show();
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");
        if (isimDuzenleme.getText().toString().equals("")) {
            isimDuzenlemeUyarisi.setVisibility(View.VISIBLE);
            isimDuzenlemeUyarisi.setText("İsminizi Giriniz");
            return false;
        }

        if(emailDuzenleme.getText().toString().equals("")) {
            emailDuzenlemeUyarisi.setVisibility((View.VISIBLE));
            emailDuzenlemeUyarisi.setText("Email Adresinizi Giriniz");
            return false;
        }

        if (sifreDuzenleme.getText().toString().equals("")) {
            sifreDuzenlemeUyarisi.setVisibility((View.VISIBLE));
            sifreDuzenlemeUyarisi.setText("Şifrenizi Giriniz");
            return false;
        }

        if (sifreDuzenlemeTekrari.getText().toString().equals("")) {
            sifreDuzenlemeTekrariUyarisi.setVisibility((View.VISIBLE));
            sifreDuzenlemeTekrariUyarisi.setText("Şifreyi Tekrar Giriniz");
            return false;
        }

        if(!sifreDuzenleme.getText().toString().equals(sifreDuzenlemeTekrari.getText().toString())){
            sifreDuzenlemeTekrariUyarisi.setVisibility(View.VISIBLE);
            sifreDuzenlemeTekrariUyarisi.setText("Şifre Eşleşmiyor");
            return false;
        }

        return true;
    }


    private void initViews(){
        Log.d(TAG, "initViews: Started");
        isimDuzenleme = findViewById(R.id.isimDuzenleme);
        emailDuzenleme = findViewById(R.id.emailDuzenleme);
        sifreDuzenleme = findViewById(R.id.sifreDuzenleme);
        sifreDuzenlemeTekrari = findViewById(R.id.sifreDuzenlemeTekrari);

        resimButonu = findViewById(R.id.resimButonu);
        kaydolButonu = findViewById(R.id.kaydolButonu);

        isimDuzenlemeUyarisi = findViewById(R.id.isimDuzenlemeUyarisi);
        emailDuzenlemeUyarisi = findViewById(R.id.emailDuzenlemeUyarisi);
        sifreDuzenlemeUyarisi = findViewById(R.id.sifreDuzenlemeUyarisi);
        sifreDuzenlemeTekrariUyarisi = findViewById(R.id.sifreDuzenlemeTekrariUyarisi);

        spinnerUlke = findViewById(R.id.spinnerUlke);
        rgCinsiyet = findViewById(R.id.rgCinsiyet);
        onayKutusu = findViewById(R.id.onayKutusu);
        parent = findViewById(R.id.parent);

    }
}