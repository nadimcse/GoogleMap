package com.example.googlemap;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
 
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
 
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity  {
	  GoogleMap map;
	  
	  private static final LatLng GOLDEN_GATE_BRIDGE = 
		        new LatLng(37.828891,-122.485884);
		 
		    private static final LatLng APPLE = 
		        new LatLng(37.3325004578, -122.03099823);
	  
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        map = ((SupportMapFragment) getSupportFragmentManager()
	                .findFragmentById(R.id.map)).getMap();
	        if (map == null) {
	            Toast.makeText(this, "Google Maps not available", 
	                Toast.LENGTH_LONG).show();
	        }
	    }
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is 
	        // present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	 
	    	 switch (item.getItemId()) {
	    	 
	         case R.id.menu_sethybrid:
	             map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	             break;
	  
	         case R.id.menu_showtraffic:
	             map.setTrafficEnabled(true);
	             break;
	  
	         case R.id.menu_zoomin:
	             map.animateCamera(CameraUpdateFactory.zoomIn());
	             break;
	  
	         case R.id.menu_gotolocation:
	             CameraPosition cameraPosition = new CameraPosition.Builder()
	                 .target(GOLDEN_GATE_BRIDGE) // Sets the center of the map to
	                                             // Golden Gate Bridge
	                 .zoom(17)                   // Sets the zoom
	                 .bearing(90) // Sets the orientation of the camera to east
	                 .tilt(30)    // Sets the tilt of the camera to 30 degrees
	                 .build();    // Creates a CameraPosition from the builder
	             map.animateCamera(CameraUpdateFactory.newCameraPosition(
	                 cameraPosition));
	             break;
	  
	         case R.id.menu_addmarker:
	  
	             // ---using the default marker---
	             /*
	             map.addMarker(new MarkerOptions() 
	                 .position(GOLDEN_GATE_BRIDGE)
	                 .title("Golden Gate Bridge") .snippet("San Francisco")
	                 .icon(BitmapDescriptorFactory
	                 .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	             */
	  
	             map.addMarker(new MarkerOptions()
	                 .position(GOLDEN_GATE_BRIDGE)
	                 .title("Golden Gate Bridge")
	                 .snippet("San Francisco")
	                 .icon(BitmapDescriptorFactory
	                 .fromResource(R.drawable.ic_launcher)));
	             break;
	  
	         case R.id.menu_getcurrentlocation:
	             // ---get your current location and display a blue dot---
	             map.setMyLocationEnabled(true);
	  
	             break;
	  
	         case R.id.menu_showcurrentlocation:
	             Location myLocation = map.getMyLocation();
	             LatLng myLatLng = new LatLng(myLocation.getLatitude(),
	                     myLocation.getLongitude());
	  
	             CameraPosition myPosition = new CameraPosition.Builder()
	                     .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
	             map.animateCamera(
	                 CameraUpdateFactory.newCameraPosition(myPosition));
	             break;
	  
	         case R.id.menu_lineconnecttwopoints:
	             //---add a marker at Apple---
	             map.addMarker(new MarkerOptions()
	                     .position(APPLE)
	                     .title("Apple")
	                     .snippet("Cupertino")
	                     .icon(BitmapDescriptorFactory.defaultMarker(
	                               BitmapDescriptorFactory.HUE_AZURE)));
	  
	             //---draw a line connecting Apple and Golden Gate Bridge---
	             map.addPolyline(new PolylineOptions()
	                 .add(GOLDEN_GATE_BRIDGE, APPLE).width(5).color(Color.RED));
	             break;
	         }
	         return true;
	     }
}
