package gr.hua.dit.android.assignment;

/* Author: it21871 Panagiotis Bellias */

/* Required classes are imported here */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// The MainActivity class from which the app starts, as declared in manifests/AndroidManifest.xml
public class MainActivity extends AppCompatActivity {

    public DatabaseHelper databaseHelper; // The database helper object which handles the connection to the sqlite database

    @Override
    protected void onCreate(Bundle savedInstanceState) { // The onCreate method that will be called when the main activity launches
        super.onCreate(savedInstanceState); // The onCreate method from the superclass AppCompatActivity
        setContentView(R.layout.activity_main); // Setting the layout resource for this activity
        databaseHelper = new DatabaseHelper(MainActivity.this); // Make a db helper object to handle the sqlite database we need

        // Instructions to the user
        Toast.makeText(this, "Insert the user's data in the text fields", Toast.LENGTH_SHORT).show();

        Button submitButton = findViewById(R.id.button1); // Get the created button from the layout to edit it
        submitButton.setOnClickListener(new View.OnClickListener() { // Set click listener to the button with the below processes
            @Override
            public void onClick(View v) { // The onClick method that define the processes which will run when user presses the button
                // Information message to the user -- Maybe this will be converted to log message
                Toast.makeText(MainActivity.this, "Inserting User...", Toast.LENGTH_SHORT).show();
                EditText userIdEditText = findViewById(R.id.editTextText1); // Get the created edit text from the layout to take the value
                String userId = userIdEditText.getText().toString(); // Take user id value from the edit text
                // Get the created edit text from the layout to take the value
                EditText longitudeEditText = findViewById(R.id.editTextNumberDecimal1);
                float longitude = Float.parseFloat(longitudeEditText.getText().toString()); // Take longitude value from the edit text
                // Get the created edit text from the layout to take the value
                EditText latitudeEditText = findViewById(R.id.editTextNumberDecimal2);
                float latitude = Float.parseFloat(latitudeEditText.getText().toString()); // Take latitude value from the edit text
                UserRecord user = new UserRecord(userId, longitude, latitude); // Create a user record object to add the values
                long id = databaseHelper.insertUser(user); // And this object gets inserted in the database
                // Information message to the user for the row id of the record that just got inserted
                Toast.makeText(MainActivity.this, "User inserted with row id: " + id, Toast.LENGTH_SHORT).show();
            }
        });

        Button nextButton = findViewById(R.id.button2); // Get the created button from the layout to edit it
        nextButton.setOnClickListener(new View.OnClickListener() { // Set click listener to the button with the below processes
            @Override
            public void onClick(View v) { // The onClick method that define the processes which will run when user presses the button
                // Information message to the user -- Maybe this will be converted to log message
                Toast.makeText(MainActivity.this, "You go on 2nd activity", Toast.LENGTH_SHORT).show();
                // Create a new intent object to pass to the next activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent); // Start the activity which is defined in the intent
                finish(); // Finish the process which starts the activity when done
            }
        });

    }

}