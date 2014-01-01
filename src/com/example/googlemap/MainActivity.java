package com.example.googlemap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction; 
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends FragmentActivity  {
	  GoogleMap map;
	  
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        FragmentManager fragmentManager = getSupportFragmentManager();
		      FragmentTransaction fragmentTransaction = 
		      fragmentManager.beginTransaction();

		      MapFragment mapFragment = new MapFragment();
		      fragmentTransaction.replace(android.R.id.content, mapFragment);
		      fragmentTransaction.commit();
	    }
}
