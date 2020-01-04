package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import eu.ase.ro.dam.subway_route.DB.table.Station;
import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Route;

import java.util.ArrayList;
import java.util.List;

public class AllStations extends AppCompatActivity {
    ListView lvStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stations);

        initView();
    }

    private void initView(){
        lvStations = findViewById(R.id.lv_allStations);

        List<Station> stations = new ArrayList<>();

        stations.add(new Station("Aparatoii Patriei",2));
        stations.add(new Station("Piata Sudului",2));
        stations.add(new Station("Constantin Brancoveanu",2));
        stations.add(new Station("Eroii Revolutiei",2));
        stations.add(new Station("Piata Unirii 2",2));
        stations.add(new Station("Universitate",2));
        stations.add(new Station("Piata Romana",2));
        stations.add(new Station("Piata Victoriei 2",2));
        stations.add(new Station("Aviatorilor",2));
        stations.add(new Station("Aurel Vlaicu",2));
        stations.add(new Station("Pipera",2));

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, stations);
        lvStations.setAdapter(adapter);
    }
}
