package eu.ase.ro.dam.subway_route;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.util_class.Route;

public class MainActivity extends AppCompatActivity {
    public static final String ROUTES_KEY = "myroutes";
    public static final int REQUEST_CODE_SEARCH_ROUTE = 14;
    private List<Route> routes = new ArrayList<>();
    FloatingActionButton fabSearchRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(),getApplicationContext().getPackageName(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_routes:
                intent = new Intent(getApplicationContext(), RoutesActivity.class);
                intent.putParcelableArrayListExtra(ROUTES_KEY, (ArrayList<Route>)routes);
                startActivity(intent);
                break;
            case R.id.item_info:
                Toast.makeText(getApplicationContext(),getApplicationContext().getPackageName(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_feedback:
                Toast.makeText(getApplicationContext(),getApplicationContext().getPackageName(),Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SEARCH_ROUTE && resultCode == RESULT_OK){
            Route route = data.getParcelableExtra(SearchRouteActivity.SEARCH_ROUTE_KEY);
            if(route != null){
                Toast.makeText(getApplicationContext(),route.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initView() {
        fabSearchRoute = findViewById(R.id.main_fab_search_route);
        fabSearchRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchRouteActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SEARCH_ROUTE);
            }
        });
    }
}
