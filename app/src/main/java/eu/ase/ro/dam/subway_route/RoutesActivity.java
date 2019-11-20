package eu.ase.ro.dam.subway_route;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.util_class.Route;

public class RoutesActivity extends AppCompatActivity {
    public static final String ROUTES_KEY = "myroutes";
    ListView lv_routes;
    List<Route> routes = new ArrayList<>();
    private ArrayAdapter<Route> adapter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
    }

    public void initView(View view) {
        lv_routes = findViewById(R.id.routes_lv_routes);
        intent = getIntent();
        routes = intent.getParcelableArrayListExtra(ROUTES_KEY);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
        lv_routes.setAdapter(adapter);
    }
}
