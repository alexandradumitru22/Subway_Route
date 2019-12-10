package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.activities.FeedbackActivity;
import eu.ase.ro.dam.subway_route.activities.InfoActivity;
import eu.ase.ro.dam.subway_route.activities.LoginActivity;
import eu.ase.ro.dam.subway_route.activities.RegisterActivity;
import eu.ase.ro.dam.subway_route.activities.RoutesActivity;
import eu.ase.ro.dam.subway_route.activities.SearchRouteActivity;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class ProfileActivity extends AppCompatActivity {
    public List<Route> routes = new ArrayList<>();
    private ImageButton ibtnUpload;
    private Button btnExit;
    private MenuItem itemLogin;
    private MenuItem itemRegister;
    private TextView starNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        itemRegister = menu.findItem(R.id.item_register);
        itemLogin= menu.findItem(R.id.item_login);

        itemRegister.setVisible(false);
        itemLogin.setVisible(false);
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
                startActivityForResult(intent, Const.SEARCH_ROUTE_CODE);
                break;
            case R.id.item_routes:
                intent = new Intent(getApplicationContext(), RoutesActivity.class);
                intent.putParcelableArrayListExtra(Const.ROUTES_KEY, (ArrayList<Route>)routes);
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
        btnExit = findViewById(R.id.profile_btn_deconectare);
        ibtnUpload = findViewById(R.id.profile_ibtn_upload);
        starNumber = findViewById(R.id.profile_tv_mark);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.info_deconectare, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ibtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.eventUpload, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        Float mark = intent.getFloatExtra(Const.STAR_TRANSFER_KEY, 0);
        starNumber.setText(mark.toString());
    }
}
