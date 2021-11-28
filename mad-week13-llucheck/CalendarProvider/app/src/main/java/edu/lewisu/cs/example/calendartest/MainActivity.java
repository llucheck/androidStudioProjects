package edu.lewisu.cs.example.calendartest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> calIds;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 123;
    private Boolean calendarPermission = false;
    private EditText eventTextView;
    private ArrayList<String> calDisplayNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCalendarPermission();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR},12);
        }

        eventTextView = findViewById(R.id.eventTextView);
        Spinner spinner = findViewById(R.id.calendarSpinner);
        calDisplayNames = new ArrayList<>();
        calIds = new ArrayList<>();

        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = CalendarContract.Calendars.VISIBLE + "=1";
        String[] calendarsProjection = new String[]{
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                //CalendarContract.Calendars.VISIBLE
        };

        Cursor cursor = getContentResolver().query(uri, calendarsProjection, selection, null, null);

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String calName = cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME));
                int calID = (int)cursor.getLong(cursor.getColumnIndex(CalendarContract.Calendars._ID));
                calIds.add(calID);
                calDisplayNames.add(calName);
            }
        }

        if(cursor != null){
            cursor.close();
        }

        if(calDisplayNames.size() == 0){
            calDisplayNames.add("no calendars found");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, calDisplayNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Uri uri = CalendarContract.Events.CONTENT_URI;

                Calendar now = Calendar.getInstance();
                long nowMillis = now.getTimeInMillis();
                String selection = "((" + CalendarContract.Events.CALENDAR_ID + " = ? ) AND (" + CalendarContract.Events.DTSTART + " > ?))";
                String[] selectionArgs = new String[]{Integer.toString(calIds.get(position)), Long.toString(nowMillis)};

                String[] projection = new String[]{
                        CalendarContract.Events._ID,
                        CalendarContract.Events.TITLE,
                        CalendarContract.Events.DTSTART,
                        CalendarContract.Events.CALENDAR_ID
                };

                String eventText = "";
                Cursor cursor1 = getContentResolver().query(uri, projection, selection, selectionArgs, null);

                if(cursor1 != null && cursor1.getCount() > 0){
                    while(cursor1.moveToNext()){
                        String eventTitle = cursor1.getString(cursor1.getColumnIndex(CalendarContract.Events.TITLE));
                        eventText = eventText + eventText + "\n";
                    }
                }

                if(cursor1 != null){
                    cursor1.close();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                eventTextView.setText("");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCalendarPermission();
    }

    /**********************PERMISSION HANDLING******************************************************/



    //if permission has been granted run query otherwise request permission to read calendar
    private void requestCalendarPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CALENDAR)
                == PackageManager.PERMISSION_GRANTED) {
            calendarPermission = true;

        }else{
            //request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALENDAR},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSIONS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    calendarPermission = true;

                }
            }
        }

    }
}
