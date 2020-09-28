package com.example.medicineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DisplayDetails extends AppCompatActivity {

    private static final String TAG = "DisplayDetails ";

    private TextView datepicker;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    RecyclerView recyclerView;
    DatabaseReference reff;

    Medicine medicine;
   String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        reff  = FirebaseDatabase.getInstance().getReference().child("Medicine");
        Query query = reff.child("Medicine");


        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datepicker = (TextView) findViewById(R.id.datepicker);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(DisplayDetails.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year );

                String date = month + "/" + day + "/" + year;
                datepicker.setText(date);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Medicine>options = new FirebaseRecyclerOptions.Builder<Medicine>()
                .setQuery(reff,Medicine.class)
                .build();

        final FirebaseRecyclerAdapter<Medicine, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medicine, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, Medicine model) {
                holder.setData(getApplicationContext(),model.getMediname(),model.getDosage(),model.getType());

                holder.setOnClickListener(new ViewHolder.Clicklistener() {
                    @Override
                    public void onItemlongClick(View view, int position) {

                        name = getItem(position).getMediname();

                        showDeleteDataDialog(name);

                    }
                });


            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,parent,false);

                return new ViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);



    }

    private  void showDeleteDataDialog(final String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayDetails.this);
        builder.setMessage("Are you sure to Delete this");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Query query = reff.orderByChild("mediname").equalTo(String.valueOf(name));
               query.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot ds : dataSnapshot.getChildren()){
                           ds.getRef().removeValue();
                       }
                       Toast.makeText(DisplayDetails.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}