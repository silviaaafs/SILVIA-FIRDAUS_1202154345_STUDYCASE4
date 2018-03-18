package com.example.android.silviafirdaus_1202154345_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class CariGambar extends AppCompatActivity {

    private EditText masukanLink;
    private Button bcariGambar;
    private ImageView tampilGambar;
    private ProgressDialog bProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        masukanLink = findViewById(R.id.etLink);
        bcariGambar = findViewById(R.id.btCari);
        tampilGambar = findViewById(R.id.ivGambar);

    }

    public void klikCari(View view) {
        loadImageInit();
    }

    private void loadImageInit(){
        String ImgUrl = masukanLink.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImgUrl);
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected  void onPreExecute() {
            super.onPreExecute();

            bProgressDialog = new ProgressDialog(CariGambar.this);

            bProgressDialog.setMessage("Loading"); //mengeset pesan dialog

            bProgressDialog.show(); //menampilkan dialog
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]); // mendownload gambar dari url
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent()); // mengkonversikan gambar ke bitmat (decode to bitmap)
            } catch (Exception e) { //karena memakain thread sleep maka harus memakai exception
                e.printStackTrace();
            }
            return bitmap;
        }

           @Override
           protected void onPostExecute(Bitmap bitmap) {
               super.onPostExecute(bitmap);
               // menampung gambar ke imageView dan menampilkannya
               tampilGambar.setImageBitmap(bitmap);

               // menghilangkan Progress Dialog
               bProgressDialog.dismiss();
           }
    }
}