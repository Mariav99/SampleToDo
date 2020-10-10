package com.example.sampletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);

        void onClickListener(int position);
    }
    public interface OnLongClickListener {
        void onItemLongClicked(int position);

       // void onIntemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }




    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ItemAdapter.ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    //Binding data to ViewHolder

    @Override
    public int getItemCount(){
        return items.size();
    }


        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                tvItem = itemView.findViewById(android.R.id.text1);
            }

            //update view inside ViewHoder

            public void bind(String item) {
                tvItem.setText(item);
                tvItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClicked(getAdapterPosition());

                    }
                });
                tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        longClickListener.onItemLongClicked(getAdapterPosition());
                        return true;
                    }
                });

            }
        }
    }
