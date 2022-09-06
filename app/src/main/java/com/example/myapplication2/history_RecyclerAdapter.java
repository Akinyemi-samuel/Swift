package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class history_RecyclerAdapter extends RecyclerView.Adapter<history_RecyclerAdapter.viewholder> {
    private List<history_Product>List ;

    public history_RecyclerAdapter(List<history_Product>List) {
       this.List = List;
    }

    @NonNull
    @Override
    public history_RecyclerAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_design,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull history_RecyclerAdapter.viewholder holder, int position) {
        holder.rec_name.setText(List.get(position).getRec_name());
        holder.rec_address.setText(List.get(position).getProduct());
        holder.order_id.setText(List.get(position).getOrder_id());
    }

    @Override
    public int getItemCount() { return List.size(); }

    public class viewholder extends RecyclerView.ViewHolder{
TextView rec_name,rec_address,order_id;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            rec_name = itemView.findViewById(R.id.profilename);
            rec_address = itemView.findViewById(R.id.product_name);
            order_id = itemView.findViewById(R.id.Order_id);
        }
    }
}
