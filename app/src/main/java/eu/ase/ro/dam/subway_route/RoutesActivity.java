package eu.ase.ro.dam.subway_route;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class RoutesActivity extends AppCompatActivity {
    //public static final String ROUTES_KEY = "myroutes";
    ListView lv_routes;
    List<Route> routes = new ArrayList<>();
    private ArrayAdapter<Route> adapter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        initView();
    }

    public void initView() {
        lv_routes = findViewById(R.id.routes_lv_routes);
        intent = getIntent();
        routes = intent.getParcelableArrayListExtra(Const.ROUTES_KEY);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
        lv_routes.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Route route = data.getParcelableExtra(Const.SEARCH_ROUTE_KEY);
        }
    }
}
