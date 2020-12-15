package gr.hua.dit.android.assignment;

/* Author: it21871 Panagiotis Bellias */

/* Required classes are imported here */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList; // ArrayList class imported here

// The ThirdActivity class
// Source for help: https://stackoverflow.com/questions/17031006/how-add-and-delete-rows-to-table-layout-in-java-programically
public class ThirdActivity extends AppCompatActivity {

    private Intent intent; // An intent object which contains from which activity we came

    @Override
    protected void onCreate(Bundle savedInstanceState) { // The onCreate method that will be called when the activity launches
        super.onCreate(savedInstanceState); // The onCreate method from the superclass AppCompatActivity
        setContentView(R.layout.activity_third); // Setting the layout resource for this activity

        //Get values with intent extras
        intent = getIntent();
        String userId = intent.getStringExtra("userId"); // Get the userId value
        long dt = intent.getLongExtra("timestamp", 0); // Get the timestamp value, if null defined as 0

        // The database helper object which handles the connection to our sqlite database
        DatabaseHelper helper = new DatabaseHelper(ThirdActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase(); // Get the database to see the results

        ArrayList<UserRecord> users = new ArrayList<>(); // The array list that will be used to keep the users' records from the database

        // Check what is empty and take results from db
        String table = DatabaseHelper.TABLE_NAME; String[] columns = new String[]{"rowid", DatabaseHelper.FIELD_1, DatabaseHelper.FIELD_2,
                DatabaseHelper.FIELD_3, DatabaseHelper.FIELD_4};
        String selection; String[] selectionArgs;
        if (!(userId.isEmpty() || dt == 0)){
            selection = DatabaseHelper.FIELD_1 + " = ? AND " + DatabaseHelper.FIELD_4 + " = ?";
            selectionArgs = new String[]{userId, dt+""};
        } else if (dt == 0 && !(userId.isEmpty())){
            selection = DatabaseHelper.FIELD_1 + " = ? ";
            selectionArgs = new String[]{userId};
        } else if (dt != 0 && userId.isEmpty()){
            selection = DatabaseHelper.FIELD_4 + " = ? ";
            selectionArgs = new String[]{dt+""};
        } else {
            selection = null;
            selectionArgs = null;
        }
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);

        // Add results in the array list
        if (cursor.moveToFirst()){
            do {
                UserRecord user = new UserRecord(cursor.getLong(0), cursor.getString(1),
                        cursor.getFloat(2), cursor.getFloat(3), cursor.getLong(4));
                users.add(user);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "There are no data for this search", Toast.LENGTH_SHORT).show();
        }

        // If there are no results go to the first activity
        if (users.isEmpty())
            returnToFirstActivity(intent);
        else // Else put it in the Table Layout with the method
            resultView(users);

        Button toFirst = findViewById(R.id.button4); // Get the created button from the layout to edit it
        toFirst.setOnClickListener(v -> { //Lambda expression to define the processes that will be done when user presses the button
            returnToFirstActivity(intent); // Go to the first activity
        });

    }

    private void returnToFirstActivity(Intent intent){ // Method to return to the first activity to insert new user records
        // Create a new intent object to pass to the next activity
        intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent); // Start the activity which is defined in the intent
        finish();  // Finish the process which starts the activity when done
    }

    private void resultView(ArrayList<UserRecord> users) { // Method to view the database results
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.resultTable); // Get the created table layout to edit it

        for (int i = 0; i < users.size(); i++) { // For every user record
            // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)); // Set layout parameters to the row

            // Creation textView1
            final TextView text1 = new TextView(this);
            String id = users.get(i).getId()+""; // Define the value that needs to be set
            text1.setText(id); // Set the TextView value
            text1.setTextColor(Color.BLACK); // Set TextView text color
            text1.setPadding(10, 10, 10, 10); // Set padding
            text1.setTextSize(14); // Set Text Size
            // Set layout parameters to the text view
            text1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(text1); // Add text view to the table row

            // Creation textView2
            final TextView text2 = new TextView(this);
            text2.setText(users.get(i).getUserId()); // Set the TextView value
            text2.setTextColor(Color.BLACK); // Set TextView text color
            text2.setPadding(10, 10, 10, 10); // Set padding
            text2.setTextSize(14); // Set Text Size
            // Set layout parameters to the text view
            text2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(text2); // Add text view to the table row

            // Creation textView3
            final TextView text3 = new TextView(this);
            String longitude = users.get(i).getLongitude()+"";  // Define the value that needs to be set
            text3.setText(longitude); // Set the TextView value
            text3.setTextColor(Color.BLACK); // Set TextView text color
            text3.setPadding(10, 10, 10, 10); // Set padding
            text3.setTextSize(14); // Set Text Size
            // Set layout parameters to the text view
            text3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(text3); // Add text view to the table row

            // Creation textView4
            final TextView text4 = new TextView(this);
            String latitude = users.get(i).getLatitude()+"";  // Define the value that needs to be set
            text4.setText(latitude); // Set the TextView value
            text4.setTextColor(Color.BLACK); // Set TextView text color
            text4.setPadding(10, 10, 10, 10); // Set padding
            text4.setTextSize(14); // Set Text Size
            // Set layout parameters to the text view
            text4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(text4); // Add text view to the table row

            // Creation textView5
            final TextView text5 = new TextView(this);
            String dt = users.get(i).getTimestamp()+"";  // Define the value that needs to be set
            text5.setText(dt); // Set the TextView value
            text5.setTextColor(Color.BLACK); // Set TextView text color
            text5.setPadding(10, 10, 10, 10); // Set padding
            text5.setTextSize(14); // Set Text Size
            // Set layout parameters to the text view
            text5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.addView(text5); // Add text view to the table row

            tableLayout.addView(tableRow); // Add the row to the table layout
        }

    }
}