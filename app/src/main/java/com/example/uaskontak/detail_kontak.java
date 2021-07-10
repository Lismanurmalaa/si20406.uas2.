package com.example.uaskontak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class detail_kontak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontak);

        TextView nama = findViewById(R.id.txt_nama);
        TextView number = findViewById(R.id.txt_number);
        ImageButton btn_back = findViewById(R.id.btn_back);
        ImageView phone = findViewById(R.id.phone);
        ImageView delete = findViewById(R.id.delete);
        ImageView message = findViewById(R.id.message);
        ImageView edit = findViewById(R.id.edit);

        String Nama = getIntent().getStringExtra("nama");
        String Number = getIntent().getStringExtra("nomor");


        nama.setText(Nama);
        number.setText(Number);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_kontak.this, "Sedang dalam pengembangan", Toast.LENGTH_LONG).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_kontak.this, "Sedang dalam pengembangan", Toast.LENGTH_LONG).show();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_kontak.this, "Sedang dalam pengembangan", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(edit.getContext(), "Sedang dalam pengembangan", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(detail_kontak.this);

                // set title dialog
                alertDialogBuilder.setTitle("Yakin hapus kontak" + Nama);

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Klik Ya untuk hapus!")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // jika tombol diklik, maka akan menutup activity ini
                                String Key = getIntent().getStringExtra("key");
                                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Kontak").child(Key);
                                dR.removeValue();
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });

    }
}