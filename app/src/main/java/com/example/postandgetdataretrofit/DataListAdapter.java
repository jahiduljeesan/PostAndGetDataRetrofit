package com.example.postandgetdataretrofit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {
    Context context;
    List<DataModel> dataList;
    ButtonHandeller buttonHandeller;



    public DataListAdapter(Context context, List<DataModel> dataList,ButtonHandeller buttonHandeller) {
        this.context = context;
        this.dataList = dataList;
        this.buttonHandeller = buttonHandeller;
    }


    @NonNull
    @Override
    public DataListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data_model_design,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataListAdapter.ViewHolder holder, int position) {
        Log.d("List",dataList+"");
        holder.tvName.setText(dataList.get(position).getName());
        holder.tvEmail.setText(dataList.get(position).getEmail());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonHandeller.onDeleteClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            button = itemView.findViewById(R.id.crossButton);
        }
    }

    public interface ButtonHandeller {
        void onDeleteClick(int position);
    }
}
