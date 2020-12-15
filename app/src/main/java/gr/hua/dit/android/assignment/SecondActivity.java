package gr.hua.dit.android.assignment;

/* Author: it21871 Panagiotis Bellias */

/* Required classes are imported here */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList; // ArrayList class imported here

// The SecondActivity class
// Source for help: https://camposha.info/android-sqlite-spinner/
public class SecondActivity extends AppCompatActivity {

    public Spinner spinner; // A spinner object to represent the dropdown list
    public ArrayList<String> timestamps = new ArrayList<String>(); // The arraylist in which all the available timestamps will be added
    public ArrayAdapter<String> adapter; // An adapter object so as to adjust the spinner object with values
    public DatabaseHelper databaseHelper; // The database helper object which handles the connection to our sqlite database

    @Override
    protected void onCreate(Bundle savedInstanceState) { // The onCreate method that will be called when the activity launches
        super.onCreate(savedInstanceState); // The onCreate method from the superclass AppCompatActivity
        setContentView(R.layout.activity_second); // Setting the layout resource for this activity
        spinner = (Spinner) findViewById(R.id.spinner); // Get the created spinner from the layout to edit it

        /*  Fill the dropdown list with data from the db
            using the adapter */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timestamps);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.FIELD_4},
                null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                //Toast.makeText(this, cursor.getString(0), Toast.LENGTH_SHORT).show(); - make it log message with if condition
                timestamps.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        spinner.setAdapter(adapter);

        Toast.makeText(this, "Insert the user id and choose the timestamp you're looking for from the list",
                Toast.LENGTH_SHORT).show(); // Instructions to the user

        Button nextButton = findViewById(R.id.button3); // Get the created button from the layout to edit it
        nextButton.setOnClickListener(new View.OnClickListener() { // Set click listener to the button with the below processes
            @Override
            public void onClick(View v) { // The onClick method that define the processes which will run when user presses the button
                // Information message to the user -- Maybe this will be converted to log message
                Toast.makeText(SecondActivity.this, "You go on 3rd activity", Toast.LENGTH_SHORT).show();
                // Create a new intent object to pass to the next activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                // Get the created edit text from the layout to take the value
                EditText editText = findViewById(R.id.editTextText2);
                // Take user id value from the edit text and declare it as extra to pass it to the next activity
                intent.putExtra("userId", editText.getText().toString());

                // Get the created spinner from the layout to take the value
                Spinner spinner = findViewById(R.id.spinner);
                long spinnerValue = Long.parseLong(spinner.getSelectedItem().toString()); // Take timestamp value from the spinner
                intent.putExtra("timestamp", spinnerValue); // Declare it as extra to pass it to the next activity
                startActivityForResult(intent, 7); // Start the activity which is defined in the intent with the declared extras
            }
        });

    }

}