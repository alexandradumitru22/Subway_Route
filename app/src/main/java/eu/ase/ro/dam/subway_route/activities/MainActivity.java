package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.activities.FeedbackActivity;
import eu.ase.ro.dam.subway_route.activities.LoginActivity;
import eu.ase.ro.dam.subway_route.activities.RegisterActivity;
import eu.ase.ro.dam.subway_route.activities.RoutesActivity;
import eu.ase.ro.dam.subway_route.activities.SearchRouteActivity;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class MainActivity extends AppCompatActivity {
    public List<Route> routes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
            case R.id.item_search_route:
                intent = new Intent(getApplicationContext(), SearchRouteActivity.class);
                startActivityForResult(intent, Const.SEARCH_ROUTE_CODE);//pentru ca o sa pasez din main in lista
                break;
            case R.id.item_routes:
                intent = new Intent(getApplicationContext(), RoutesActivity.class);//ma duce in routes
                intent.putParcelableArrayListExtra(Const.ROUTES_KEY, (ArrayList<Route>)routes);//pe intent pun ruta facuta in search
                startActivityForResult(intent,Const.ROUTES_CODE);
                break;
            case R.id.item_info:
                intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.item_feedback:
                intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK && data != null) && (requestCode == Const.SEARCH_ROUTE_CODE)) {
            Route route = data.getParcelableExtra(Const.SEARCH_ROUTE_KEY);
            if (route != null) {
                addRoute(route);
                Toast.makeText(getApplicationContext(), route.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addRoute(Route r){
        routes.add(r);
    }

    private void initView() {
        routes = new ArrayList<>();
    }
}
