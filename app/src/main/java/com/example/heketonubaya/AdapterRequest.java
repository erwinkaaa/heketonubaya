package com.example.heketonubaya;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterRequest extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<POJO_Request> pojo_requests;
    Context context;

    public AdapterRequest(ArrayList<POJO_Request> pojo_requests, Context context){
        this.pojo_requests = pojo_requests;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_request, parent, false);
        final RecyclerView.ViewHolder mViewHolder = new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView textView = holder.itemView.findViewById(R.id.tv_item_name);
        TextView textView1 = holder.itemView.findViewById(R.id.tv_from);
        TextView textView2 = holder.itemView.findViewById(R.id.tv_tanggal);
        TextView textView3 = holder.itemView.findViewById(R.id.tv_keterangan);

        textView.setText(pojo_requests.get(position).getTitle());
        textView1.setText("From: "+pojo_requests.get(position).getFrom());
        textView2.setText(pojo_requests.get(position).getDate_req());
        textView3.setText(pojo_requests.get(position).getContent());

        Button btnTerima, btnTolak;
        btnTerima = holder.itemView.findViewById(R.id.btnTerima);
        btnTolak = holder.itemView.findViewById(R.id.btnTolak);
        btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem2(position);
            }
        });

        LinearLayout llDownload = holder.itemView.findViewById(R.id.llDonwload);
        llDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "file downloaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeItem(int position){
        pojo_requests.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, pojo_requests);
    }

    public void removeItem2(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alasan : ");

        final EditText input = new EditText(context);
        input.setText("");
        builder.setView(input);

        builder.setPositiveButton("Tolak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty()) {
                    pojo_requests.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position, pojo_requests);
                } else {

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public int getItemCount() {
        return pojo_requests.size();
    }
}
