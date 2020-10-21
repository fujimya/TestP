package com.example.testp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView statusTxt = findViewById(R.id.txtKondisi);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference status = database.getReference("rekam_data/palang");

        status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Toast.makeText(MainActivity.this,dataSnapshot.toString(),Toast.LENGTH_LONG).show();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    //Toast.makeText(MainActivity.this,child.child("data").getValue().toString(),Toast.LENGTH_LONG).show();
                    String kondisi =  child.child("data").getValue().toString();
                    statusTxt.setText(kondisi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}