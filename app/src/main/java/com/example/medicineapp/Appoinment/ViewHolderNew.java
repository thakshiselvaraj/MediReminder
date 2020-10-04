package com.example.medicineapp.Appoinment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicineapp.R;

public class ViewHolderNew extends RecyclerView.ViewHolder {
    public ViewHolderNew(@NonNull View itemView) {
        super(itemView);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context context, String cname, String sp, String docName, String Add, int cphone, String date, String time){
        TextView textView = itemView.findViewById(R.id.txtViewRow);
        textView.setText("Center Name : "+cname+ "\n" + "Specialization : "+ sp + "\n"+ "Doctor Name : "+docName+ "\n"
                + "Location : "+Add+ "\n"+ "Phone : "+cphone+ "\n"+ "Date : "+date+ "\n"+ "Time : "+time);
    }

    private ViewHolderNew.Clicklistener mClickListener;

    public interface Clicklistener{
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolderNew.Clicklistener clickListener)
    {
        mClickListener = clickListener;
    }
}
