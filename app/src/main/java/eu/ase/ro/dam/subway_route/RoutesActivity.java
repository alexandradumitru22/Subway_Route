package eu.ase.ro.dam.subway_route;

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

import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class RoutesActivity extends AppCompatActivity {
    ListView lv_routes;
    List<Route> routes = new ArrayList<>();
    Intent intent;
    //Adapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        lv_routes = findViewById(R.id.routes_lv_routes);
        intent = getIntent(); //am luat intentul
        ArrayList<Route> list = intent.getParcelableArrayListExtra(Const.ROUTES_KEY); //am luat lista in listview
        if( list!=null ){
            routes = list;
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, routes);
        lv_routes.setAdapter(adapter);
    }


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Route route = routes.get(position);
        Intent intent = new Intent(getApplicationContext(), SearchRouteActivity.class);
        intent.putExtra(Const.POSITION_KEY, position);
        intent.putExtra(Const.SEARCH_ROUTE_KEY, route);
        startActivityForResult(intent, 2);
    }*/

    /*@Override
    public void onBackPressed() {
        intent=getIntent();
        intent.putExtra(Const.ROUTES_KEY, (Parcelable) routes);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }*/
}
