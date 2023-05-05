package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<MyObject> contacts = new ArrayList<>();
    private FloatingActionButton addFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouterContact();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, contacts));
        addFab = findViewById(R.id.add_fab);
        addFab.setOnClickListener(v -> {
            //start AddContact Activity
            startActivity(new Intent(MainActivity.this, AddContact.class));
        });


    }

    public void ajouterContact(){
        contacts.add(new MyObject("imen", 123));
        contacts.add(new MyObject("bro", 2764582));
        contacts.add(new MyObject("hello", 00000));

    }
}