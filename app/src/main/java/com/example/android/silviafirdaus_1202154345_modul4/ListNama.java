package com.example.android.silviafirdaus_1202154345_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {

    private ListView bListMahasiswa;
    private Button bStartAsyncTask;
    private ProgressBar bProgressBar;
    private String[] bahasiswaArray = {
            "Silvia Firdaus", "Rizka Nursyahdilla", "Farrel Ramadhika", "Ahmad Fakhri", "Desfari Aditian", "Astrid Shofi", "Dewi Purnamasari", "Ina Indah",
            "Jafar Haritsah", "Eka Irianto"};
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        bListMahasiswa = findViewById(R.id.listMahasiswa);
        bProgressBar = findViewById(R.id.progressBar);
        bStartAsyncTask = findViewById(R.id.btMulai);

        bListMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        bStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();

            }
        });
    }
    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private  ArrayAdapter<String> bAdapter;
        private int counter = 1;
        ProgressDialog bProgressDialog = new ProgressDialog(ListNama.this);

        @Override
        protected void onPreExecute(){
            bAdapter = (ArrayAdapter<String>) bListMahasiswa.getAdapter();
            //mengisi dialog yang ingin di tampilkan
            bProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            bProgressDialog.setTitle("Loading Data");
            bProgressDialog.setCancelable(false);
            bProgressDialog.setMessage("Please wait....");
            bProgressDialog.setProgress(0);
            bProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    bProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            bProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : bahasiswaArray) {
                publishProgress(item);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            bAdapter.add(values[0]);

            Integer current_status = (int) ((counter / (float) bahasiswaArray.length) * 100);
            bProgressBar.setProgress(current_status);

            bProgressDialog.setProgress(current_status);

            bProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //hide progreebar
            bProgressBar.setVisibility(View.GONE);

            //remove progress dialog
            bProgressDialog.dismiss();
            bListMahasiswa.setVisibility(View.VISIBLE);
        }
    }
}
