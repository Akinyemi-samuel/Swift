package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {

    private List<Product>List;
    private ItemClickListener clickListener;


    public RecyclerAdapter (ItemClickListener clickListener,List<Product>List)
    {this.List=List;
    this.clickListener = clickListener; }


    @NonNull
    @Override
    public RecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ordernumb.setText(List.get(position).getOrder_id());
        holder.receipentname.setText(List.get(position).getReceipient_name());
        holder.receipent_phone.setText(List.get(position).getReceipient_Phone());
        holder.deliveryLocation.setText(List.get(position).getReciepient_Address());


        holder.order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(List.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
TextView ordernumb,receipentname,receipent_phone,deliveryLocation,order_details;
Button order_btn;
        public viewHolder(@NonNull View view)
        {
            super(view);
            ordernumb = view.findViewById(R.id.order_id);
            receipentname = view.findViewById(R.id.receipent_name);
            receipent_phone = view.findViewById(R.id.receipent_phone);
            deliveryLocation = view.findViewById(R.id.deliverylocation);
            order_btn = view.findViewById(R.id.order_btn);
        }


    }
    public interface ItemClickListener{
        void onItemClick(Product product);
    }
}
