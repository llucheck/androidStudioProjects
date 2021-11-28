package edu.lewisu.cs.example.implicitintents;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void onClickWebsite(View v){
         Uri uri = Uri.parse("http://cs.lewisu.edu");
         Intent intent = new Intent(Intent.ACTION_VIEW, uri);
         if(intent.resolveActivity(getPackageManager()) != null){
             startActivity(intent);
         }
     }

     public void onClickSearchWeb(View v){
        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY, "Boiled Eggs");
        if(searchIntent.resolveActivity(getPackageManager()) != null){
            startActivity(searchIntent);
        }
     }

     public void onClickViewContacts(View v){
        Intent contactsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
        if(contactsIntent.resolveActivity(getPackageManager()) != null){
            startActivity(contactsIntent);
        }
     }

     public void onClickPhoneCall(View v){
        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18158365145"));
        if(callIntent.resolveActivity(getPackageManager())!= null){
            startActivity(callIntent);
        }
     }

     public void onClickShareText(View v){
        String textToShare = "Not Elephant!";
        String mimeType = "text/plain";

        String title = "Sharing Text";

        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(title).setText(textToShare).startChooser();
     }

     public void onClickOpenAddress(View v){
        Uri locationUri = Uri.parse("geo:0,0?q=Lewis+University+Illinois");

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(locationUri);
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
     }
}
