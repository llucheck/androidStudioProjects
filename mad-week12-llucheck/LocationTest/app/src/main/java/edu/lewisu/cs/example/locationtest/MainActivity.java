package edu.lewisu.cs.example.locationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    //static variables used permission verification
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 123;


    private Boolean requestingLocationUpdates;
    private Boolean deniedPermissions;




    // UI components
    private Button startUpdatesButton;
    private Button stopUpdatesButton;
    private TextView lastUpdateTimeTextView;
    private TextView LatitudeTextView;
    private TextView longitudeTextView;

    // Labels
    private String latitudeLabel;
    private String longitudeLabel;
    private String lastUpdateTimeLabel;
    private String lastUpdateTime;


    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Location location;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the UI widgets.
        startUpdatesButton = findViewById(R.id.start_updates_button);
        stopUpdatesButton = findViewById(R.id.stop_updates_button);
        LatitudeTextView = findViewById(R.id.latitude_text);
        longitudeTextView = findViewById(R.id.longitude_text);
        lastUpdateTimeTextView = findViewById(R.id.last_update_time_text);

        // Set labels.
        latitudeLabel = getResources().getString(R.string.latitude_label);
        longitudeLabel = getResources().getString(R.string.longitude_label);
        lastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);
        lastUpdateTime = "";

        requestingLocationUpdates = false;
        deniedPermissions = false;



        // Kick off the process of building the LocationCallback, LocationRequest, and
        // LocationSettingsRequest objects.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        createLocationCallback();
        createLocationRequest();


    }

    protected void createLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
    }

    private void createLocationCallback(){
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                super.onLocationResult(locationResult);
                location = locationResult.getLastLocation();
                lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                updateUI();
            }
        };
    }

    private void startLocationUpdates(){
        try{
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            updateUI();
        }catch(Exception e){
            Log.i(TAG, "This should never happen");
        }
    }

    public void startUpdatesButtonHandler(View v){
        if(!requestingLocationUpdates){
            if(checkPermissions()){
                requestingLocationUpdates = true;
                setButtonsEnabledState();
                startLocationUpdates();
            }else{
                requestPermissions();
            }
        }
    }

    public void stopUpdatesButtonHandler(View v){
        stopLocationUpdates();
    }

    private void stopLocationUpdates(){
        if(!requestingLocationUpdates){
            Log.d(TAG, "stopLocationUpdates: updates never requested");
        }else{
            fusedLocationProviderClient.removeLocationUpdates(locationCallback).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    requestingLocationUpdates = false;
                    setButtonsEnabledState();
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (requestingLocationUpdates && checkPermissions()) {
           //start location updates
            startLocationUpdates();

        } else if (!checkPermissions() && !deniedPermissions) {
            requestPermissions();
        }

        updateUI();
    }

    @Override
    protected void onPause(){
        super.onPause();
        stopLocationUpdates();
    }


    private void updateUI() {
        setButtonsEnabledState();
        if (location != null) {
            String latString  = getResources().getString(R.string.location_string, latitudeLabel, location.getLatitude());
            String lonString = getResources().getString(R.string.location_string, longitudeLabel, location.getLongitude());
            String timeString = getResources().getString(R.string.time_string, lastUpdateTimeLabel, lastUpdateTime);

            LatitudeTextView.setText(latString);
            longitudeTextView.setText(lonString);
            lastUpdateTimeTextView.setText(timeString);
        }
    }


    private void setButtonsEnabledState() {
        if (requestingLocationUpdates) {
            startUpdatesButton.setEnabled(false);
            stopUpdatesButton.setEnabled(true);
        } else {
            startUpdatesButton.setEnabled(true);
            stopUpdatesButton.setEnabled(false);
        }
    }




  /**********************PERMISSION HANDLING******************************************************/

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        if (shouldProvideRationale) {
            Snackbar.make(findViewById(android.R.id.content),R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
            .setAction(
                    android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
          if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (requestingLocationUpdates) {
                    //start location updates

                }
          } else {
                deniedPermissions = true;
                Snackbar.make(findViewById(android.R.id.content),R.string.permission_denied_explanation, Snackbar.LENGTH_INDEFINITE)
                       .setAction(R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).show();
            }
        }
    }

}
