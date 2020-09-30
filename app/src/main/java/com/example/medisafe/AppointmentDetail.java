package com.example.medisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AppointmentDetail extends AppCompatActivity {

    DatabaseReference dbRef;
    String name;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    Appointment appointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        recyclerView = findViewById(R.id.detailView);

       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbRef = FirebaseDatabase.getInstance().getReference().child("Appointment");
    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Appointment> options = new FirebaseRecyclerOptions.Builder<Appointment>()
                .setQuery(dbRef,Appointment.class).build();

        FirebaseRecyclerAdapter<Appointment, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Appointment, ViewHolder>(options){

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Appointment model) {

                holder.setData(getApplicationContext(), model.getName(), model.getSpecial(), model.getDocName(),
                        model.getAddress(), model.getPhone(), model.getDate(), model.getTime());

                holder.setOnClickListener(new ViewHolder.Clicklistener() {
                    @Override
                    public void onItemLongClick(View view, int position) {

                        name = getItem(position).getName();
                        showDeleteDialog(name);
                    }
                });
            }
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
                return new ViewHolder(view);
            }
        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    private void showDeleteDialog(final String name){

        AlertDialog .Builder builder = new AlertDialog.Builder(AppointmentDetail.this);
        builder.setTitle("Delete");
        builder.setMessage("Sure You want to delete this?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                Query query = dbRef.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(AppointmentDetail.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}