package com.example.android.silviafirdaus_1202154345_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listNama(View view) {
        Intent a = new Intent(MainActivity.this,ListNama.class);
        startActivity(a);
    }

    public void cariGambar(View view) {
        Intent b = new Intent(MainActivity.this,CariGambar.class);
        startActivity(b);
    }
}
