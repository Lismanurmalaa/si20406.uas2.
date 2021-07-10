package com.example.uaskontak.paket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaskontak.R;
import com.example.uaskontak.detail_kontak;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AdapterDataku extends RecyclerView.Adapter<AdapterDataku.ViewHolder> {
    Context context;
    ArrayList<Dataku> list;

    public AdapterDataku(ArrayList<Dataku> list,Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nama,txt_nomer, detailNama, detailNomor;
        LinearLayout list_item;
        //ImageButton tombol_edit, tombol_hapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nama = itemView.findViewById(R.id.text_nama);
            txt_nomer = itemView.findViewById(R.id.text_number);
            detailNama = itemView.findViewById(R.id.txt_nama);
            detailNomor = itemView.findViewById(R.id.txt_number);
            list_item = itemView.findViewById(R.id.list_item);
            //tombol_edit = itemView.findViewById(R.id.tombol_edit);
            //tombol_hapus = itemView.findViewById(R.id.tombol_hapus);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data_layout,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final String Nama = list.get(position).getNama();
        final String Number = list.get(position).getNumber();
        final String Key = list.get(position).getKey();

        holder.txt_nama.setText(Nama);
        holder.txt_nomer.setText(Number);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Anda klik "+list.get(position).getNama(),Toast.LENGTH_LONG).show();

                Intent i = new Intent(context.getApplicationContext(), detail_kontak.class);
                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("key", Key);
                i.putExtra("nama" ,Nama);
                i.putExtra("nomor" ,Number);
                context.getApplicationContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
