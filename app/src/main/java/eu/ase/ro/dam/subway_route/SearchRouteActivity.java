package eu.ase.ro.dam.subway_route;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.util_class.Route;

public class SearchRouteActivity extends AppCompatActivity {
    public static final String SEARCH_ROUTE_KEY = "searchRoute";
    TextInputEditText etFrom;
    TextInputEditText etDestination;
    RadioGroup rgLenght;
    RadioButton rbYes;
    RadioButton rbNo;
    CheckBox cbSave;
    Button btnSeach;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_route);

        initView();
        intent = getIntent();
    }

    private void initView(){
        etFrom = findViewById(R.id.search_route_et_from);
        etDestination = findViewById(R.id.search_route_et_to);
        rgLenght = findViewById(R.id.search_route_rg_shortest_route);
        rbYes = findViewById(R.id.search_route_rb_yes);
        rbNo = findViewById(R.id.search_route_rb_no);
        cbSave = findViewById(R.id.search_route_cb_save);
        btnSeach = findViewById(R.id.search_route_btn_search);

        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoValidation()){
                    Route route = createRoute();
                    Toast.makeText(getApplicationContext(), route.toString(), Toast.LENGTH_LONG).show();
                    intent.putExtra(SEARCH_ROUTE_KEY, route);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private Route createRoute(){
        String depart = etFrom.getText().toString();
        String dest = etDestination.getText().toString();
        RadioButton srResponse = findViewById(rgLenght.getCheckedRadioButtonId());
        String shortRoute = srResponse.getText().toString();
        return new Route(depart, dest, shortRoute);
    }

    private boolean infoValidation(){
        if(etFrom.getText() == null || etFrom.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceti statia de plecare!", Toast.LENGTH_LONG).show();
            return false;
        }
        if(etDestination.getText() == null || etDestination.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceti destinatia!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
