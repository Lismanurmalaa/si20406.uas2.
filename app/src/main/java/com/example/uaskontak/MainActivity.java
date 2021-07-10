package com.example.uaskontak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uaskontak.paket.AdapterDataku;
import com.example.uaskontak.paket.Dataku;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageButton btn_add;
    RecyclerView recyclerView;
    private DatabaseReference database;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Dataku> dataku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance().getReference();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTambahData();
            }
        });

        BacaData();
    }

    private void BacaData() {
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Kontak");
        myRef.
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataku = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Dataku value = snapshot.getValue(Dataku.class);

                    if (value != null) {
                        dataku.add(value);
                    }
                }
                adapter = new AdapterDataku(dataku,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    private void MyRecyclerView(){
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void showDialogTambahData() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.tambah_data_layout);

        TextView TombolKeluar = bottomSheetDialog.findViewById(R.id.tombol_keluar);
        TextView add_foto = bottomSheetDialog.findViewById(R.id.add_foto);
        EditText TxtNama =bottomSheetDialog.findViewById(R.id.edittxt_nama);
        EditText TxtNomer = bottomSheetDialog.findViewById(R.id.edittxt_nomer);
        TextView TombolTambah = bottomSheetDialog.findViewById(R.id.tombol_tambah);

        bottomSheetDialog.show();

        add_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Sedang dalam pengembangan",Toast.LENGTH_LONG).show();
            }
        });

        TombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        TombolTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = database.push().getKey();
                String nama = TxtNama.getText().toString();
                String nomer = TxtNomer.getText().toString();

                if(isEmpty(nama) && isEmpty(nomer)){
                    Toast.makeText(getApplicationContext(),"Nama dan Nomor tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                } else{
                    database = FirebaseDatabase.getInstance().getReference("Kontak");
                    Dataku dataku = new Dataku(key, nama, nomer);

                    //Saving the Artist
                    database.child(key).setValue(dataku);
                    bottomSheetDialog.dismiss();
                    //Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

}