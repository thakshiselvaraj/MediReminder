package com.example.medicineapp;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mclicklistener.onItemlongClick(view,getAdapterPosition());

                return false;
            }
        });
    }
    public void setData(Context context, String mediName, Float dosage, String type ){

       TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("MedicineName: " + mediName + "\n" + "Dosage: " + dosage + "\n" + "MedicineType: " + type);

    }

    private ViewHolder.Clicklistener mclicklistener;
    public interface  Clicklistener{
        void onItemlongClick(View view,int position);

    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mclicklistener = clickListener;
    }


}
