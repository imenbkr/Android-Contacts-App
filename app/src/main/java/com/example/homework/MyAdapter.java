package com.example.homework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    private List<MyObject> myObjectList; //stores a list of MyObject instances.
    //private DatabaseHolder dbHolder; //a database holder instance for updating the database.

    public MyAdapter(Context context, List<MyObject> myObjectList) {
        this.context = context;
        this.myObjectList = myObjectList;
        //this.dbHolder = dbHolder;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the cardview_layout.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);
        //create a new MyViewHolder instance with the inflated view.
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get position of instance : myObject
        MyObject myObject = myObjectList.get(position);
        //data binding to the views in the MyViewHolder
        holder.nameTextView.setText(String.valueOf(myObject.getName()));
        holder.numberTextView.setText(String.valueOf(myObject.getNumber()));

        //set click listeners for the edit and delete buttons
        holder.editButton.setOnClickListener(v -> {
            // Create an intent to start the EditContactActivity
            Intent intent = new Intent(v.getContext(), EditContactActivity.class);

            // Put the name and number of the MyObject instance into the intent
            intent.putExtra("name", myObject.getName());
            intent.putExtra("number", myObject.getNumber());

            // Start the activity with the intent
            v.getContext().startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Remove the contact from the list
            myObjectList.remove(position);

            // Notify the adapter that the data set has changed
            notifyDataSetChanged();

            // Update the database with the new list of MyObject instances
            //dbHolder.updateContact((MyObject) myObjectList);
        });
    }

    @Override
    public int getItemCount() {
        //return size of list of MyObject instances
        return myObjectList.size();
    }

    public void updateContact(int position, String editedName, int editedNumber) {
        // Update the MyObject instance at the given position with the edited name and number
        MyObject myObject = myObjectList.get(position);
        myObject.setName(editedName);
        myObject.setNumber(editedNumber);

        // Notify the adapter that the data set has changed
        notifyDataSetChanged();

        // Update the database with the new list of MyObject instances
        //dbHolder.updateContact((MyObject) myObjectList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, numberTextView;
        public ImageButton editButton, deleteButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            numberTextView = itemView.findViewById(R.id.number);
            editButton = itemView.findViewById(R.id.edit_btn);
            deleteButton = itemView.findViewById(R.id.delete_btn);
        }
    }
}
