package gr.hua.dit.android.assignment;

/* Author: it21871 Panagiotis Bellias */

import android.util.Log;

// The UserRecord class which represents a user's record
public class UserRecord {

    /* User's attributes*/
    private long id;
    private final String userId;
    private final float longitude, latitude;
    private final long timestamp;

    /* Constuctors needed */
    public UserRecord(long id, String userId, float longitude, float latitude, long timestamp) {
        Log.println(Log.INFO, "Info message", "User created");
        this.id = id;
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public UserRecord(String userId, float longitude, float latitude) {
        Log.println(Log.INFO, "Info message", "User created");
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        timestamp = System.currentTimeMillis();
    }

    /* Getters needed */
    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

}