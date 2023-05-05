package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditContactActivity extends AppCompatActivity {

    private EditText nameEditText, numberEditText;
    private String originalName, originalNumber;
    private List<MyObject> myObjectList;
    private MyAdapter myAdapter;
    private DatabaseHolder dbHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        // Get the original name and number from the intent
        Intent intent = getIntent();
        originalName = intent.getStringExtra("name");
        originalNumber = intent.getStringExtra("number");

        // Find the EditText views in the layout
        nameEditText = findViewById(R.id.edit_name);
        numberEditText = findViewById(R.id.edit_number);

        // Display the original name and number in the EditText views
        nameEditText.setText(originalName);
        numberEditText.setText(originalNumber);

        // Find the Save button in the layout
        Button saveButton = findViewById(R.id.save_edit_button);
        saveButton.setOnClickListener(v -> {
            // Get the edited name and number from the EditText views
            String editedName = nameEditText.getText().toString();
            String editedNumber = numberEditText.getText().toString();

            // Find the index of the MyObject instance in the myObjectList
            int index = getIndex(originalName, originalNumber);

            // Update the MyObject instance with the edited name and number
            MyObject myObject = myObjectList.get(index);
            myObject.setName(editedName);
            myObject.setNumber(Integer.parseInt(editedNumber));

            // Notify the adapter that the data set has changed
            myAdapter.notifyDataSetChanged();

            // Update the database with the edited contact information
            dbHolder= new DatabaseHolder(EditContactActivity.this);
            dbHolder.updateContact(myObject);

            // Finish the activity and return to the MainActivity
            finish();
        });

    }
        private int getIndex(String name, String number) {
        for (int i = 0; i < myObjectList.size(); i++) {
            MyObject myObject = myObjectList.get(i);
            if (myObject.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
