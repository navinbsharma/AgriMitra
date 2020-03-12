package com.example.agrimitra.views.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agrimitra.R;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder>
{
    private String[] data;
    public MarketAdapter(String[] data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.market_list_layout,parent,false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
    String title = data[position];
    holder.txtTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MarketViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView distance;
        ImageView arrow;

        public MarketViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.m_name);
            distance = itemView.findViewById(R.id.distance);
            arrow = itemView.findViewById(R.id.next);
        }

    }
}
