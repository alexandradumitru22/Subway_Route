package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import eu.ase.ro.dam.subway_route.DB.table.Station;
import eu.ase.ro.dam.subway_route.R;

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

        stations.add(new Station(getString(R.string.AparatoriiPatriei) ,2));
        stations.add(new Station(getString(R.string.PiataSudului),2));
        stations.add(new Station(getString(R.string.ConstBr),2));
        stations.add(new Station(getString(R.string.Eroiirev),2));
        stations.add(new Station(getString(R.string.PiataUn),2));
        stations.add(new Station(getString(R.string.univ),2));
        stations.add(new Station(getString(R.string.piatrom),2));
        stations.add(new Station(getString(R.string.piatavict),2));
        stations.add(new Station(getString(R.string.aiat),2));
        stations.add(new Station(getString(R.string.aurica),2));
        stations.add(new Station(getString(R.string.pipera),2));

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, stations);
        lvStations.setAdapter(adapter);
    }
}
