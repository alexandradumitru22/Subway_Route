package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Feedback;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_class.RouteAdapter;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class RoutesActivity extends AppCompatActivity {
    ListView lv_routes;
    Route route;
    List<Route> routes = new ArrayList<>();
    Intent intent;
    //int selectedRouteIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        initView();

        getRouteListFromFirebase();
    }

    private void initView (){
        lv_routes = findViewById(R.id.routes_lv_routes);
        intent = getIntent();
        ArrayList<Route> list = intent.getParcelableArrayListExtra(Const.ROUTES_KEY);
        if( list!=null ){
            routes = list;
        }

        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
        RouteAdapter adapter = new RouteAdapter(getApplicationContext(), R.layout.route_adapter, routes, getLayoutInflater());
        lv_routes.setAdapter(adapter);

        lv_routes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AllStations.class);
                /*selectedRouteIndex = position;
                intent.putExtra(Const.SEARCH_ROUTE_KEY, routes.get(position));*/
                startActivity(intent);
            }
        });
    }

    private void getRouteListFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child("rute").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for(DataSnapshot child : dataSnapshot.getChildren()){
                    String user = child.getValue(Route.class).getUsername();
                    String depart = child.getValue(Route.class).getDepart();
                    String destination = child.getValue(Route.class).getDestination();
                    Date date = child.getValue(Route.class).getDate();
                    String type = child.getValue(Route.class).getType();
                    String shortestRoute = child.getValue(Route.class).getShortestRoute();

                    Route r = new Route();

                    r.setUsername(user);
                    r.setDepart(depart);
                    r.setDestination(destination);
                    r.setDate(date);
                    r.setType(type);
                    r.setShortestRoute(shortestRoute);

                    if(user.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        routes.add(r);
                    }

                    if(routes != null && !routes.isEmpty()){
                        int poz = routes.size()-1;
                        route = routes.get(poz);

                        String f = route.getDepart();
                        String t = route.getDestination();
                        String d = String.valueOf(route.getDate());
                        String ty = route.getType();
                        String s = route.getShortestRoute();
                    }
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Const.EDIT_ROUTE_CODE && resultCode == RESULT_OK && data != null){
            Route route = data.getParcelableExtra(Const.SEARCH_ROUTE_KEY);
            if(route != null){
                //updateRoute(route);
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
    }*/
}
