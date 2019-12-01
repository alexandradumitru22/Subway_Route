package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_class.RouteAdapter;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class RoutesActivity extends AppCompatActivity {
    ListView lv_routes;
    List<Route> routes = new ArrayList<>();
    Intent intent;
    int selectedRouteIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        initView();
    }

    private void initView (){
        lv_routes = findViewById(R.id.routes_lv_routes);
        intent = getIntent(); //am luat intentul
        ArrayList<Route> list = intent.getParcelableArrayListExtra(Const.ROUTES_KEY); //am luat lista in listview
        if( list!=null ){
            routes = list;
        }

        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
        RouteAdapter adapter = new RouteAdapter(getApplicationContext(), R.layout.route_adapter, routes, getLayoutInflater());
        lv_routes.setAdapter(adapter);

        lv_routes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SearchRouteActivity.class);
                selectedRouteIndex = position;
                intent.putExtra(Const.SEARCH_ROUTE_KEY, routes.get(position));
                startActivityForResult(intent, Const.EDIT_ROUTE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Const.EDIT_ROUTE_CODE && resultCode == RESULT_OK && data != null){
            Route route = data.getParcelableExtra(Const.SEARCH_ROUTE_KEY);
            if(route != null){
                updateRoute(route);
                RouteAdapter adapter = (RouteAdapter) lv_routes.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRoute(Route r){
        routes.get(selectedRouteIndex).setDepart(r.getDepart());
        routes.get(selectedRouteIndex).setDestination(r.getDestination());
        routes.get(selectedRouteIndex).setDate(r.getDate());
        routes.get(selectedRouteIndex).setType(r.getType());
        routes.get(selectedRouteIndex).setShortestRoute(r.getShortestRoute());
    }
}
