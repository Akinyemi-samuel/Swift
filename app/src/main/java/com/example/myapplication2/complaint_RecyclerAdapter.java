package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class complaint_RecyclerAdapter extends RecyclerView.Adapter<complaint_RecyclerAdapter.viewholder> {

    private List<complaint_Product>List ;
    private ItemClickListener clickListener;

    public complaint_RecyclerAdapter(ItemClickListener clickListener,List<complaint_Product>List) {
        this.List = List;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public complaint_RecyclerAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_design,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull complaint_RecyclerAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {
holder.titletext.setText(List.get(position).getTitle());
holder.datetxt.setText(List.get(position).getDate());

holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titletext,datetxt;
        public viewholder(View view){
            super(view);

            titletext = view.findViewById(R.id.title);
            datetxt = view.findViewById(R.id.date);
        }

    }

    public interface ItemClickListener{

        public void onItemClick(complaint_Product complaint_product);

    }
}
