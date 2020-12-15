package gr.hua.dit.android.assignment;

/* Author: it21871 Panagiotis Bellias */

/* Required classes are imported here */
import android.content.*;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

// The DatabaseHelper class to handle the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {

    /* Define db name, table name, fields' name and creation query */
    public static String DB_NAME = "UsersDataBase", TABLE_NAME = "Users";
    public static String FIELD_1 = "userid", FIELD_2 = "longitude", FIELD_3 = "latitude", FIELD_4 = "dt";
    public static String SQL_QUERY_FOR_TABLE_CREATION = "CREATE TABLE " + TABLE_NAME + " (" +
            FIELD_1 + " TEXT " + "NOT NULL " + "DEFAULT NULL, " +
            FIELD_2 + " REAL " + "NOT NULL " + "DEFAULT 0.0, " +
            FIELD_3 + " REAL " + "NOT NULL " + "DEFAULT 0.0, " +
            FIELD_4 + " INTEGER " + "NOT NULL " + "DEFAULT 0" +
            ")";

    //Constructor for the class to set context
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // The onCreate method that will be called when the main activity launches
        db.execSQL(SQL_QUERY_FOR_TABLE_CREATION); // Execute create query
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // The onUpgrade which is unused for now
    }

    public long insertUser(UserRecord user){ // Method that inserts user's records in the database
        ContentValues values = new ContentValues(); // using contentValues object
        values.put(FIELD_1, user.getUserId()); // Set field 1 value
        values.put(FIELD_2, user.getLongitude()); // Set field 2 value
        values.put(FIELD_3, user.getLatitude()); // Set field 3 value
        values.put(FIELD_4, user.getTimestamp()); // Set field 4 value
        return this.getWritableDatabase().insert(TABLE_NAME, null, values); // And the row id is returned
    }

}
