package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MenuItem itemSearch;
    private MenuItem itemRoutes;
    private MenuItem itemFeedback;

    private MapView myMap;
    private GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(Const.MAP_VIEW_BUNDLE_KEY);
        }

        myMap.onCreate(mapViewBundle);
        myMap.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        itemSearch = menu.findItem(R.id.item_search_route);
        itemRoutes = menu.findItem(R.id.item_routes);
        itemFeedback = menu.findItem(R.id.item_feedback);

        itemSearch.setVisible(false);
        itemRoutes.setVisible(false);
        itemFeedback.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.item_register:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.item_login:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.item_info:
                intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(Const.MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(Const.MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        myMap.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        myMap.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myMap.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myMap.onStop();
    }
    @Override
    protected void onPause() {
        myMap.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        myMap.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        myMap.onLowMemory();
    }

    private void initView() {
        myMap = findViewById(R.id.mv_maps);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng buc = new LatLng(44.426843,26.1015608);
        gmap.addMarker(new MarkerOptions().position(buc).title(getString(R.string.buc)));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(buc));
        gmap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
    }
}
