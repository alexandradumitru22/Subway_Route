package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class SearchRouteActivity extends AppCompatActivity {
    public static final String MY_DATE_FORMAT = "dd.MM.yyyy";
    TextInputEditText etFrom;
    TextInputEditText etDestination;
    TextInputEditText etDate;
    Spinner spinType;
    RadioGroup rgLenght;
    RadioButton rbYes;
    RadioButton rbNo;
    Button btnSearch;
    Intent intent;
    int ok = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_route);

        initView();
        intent = getIntent();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoValidation()){
                    Route route = createSearchedRoute();
                    Toast.makeText(getApplicationContext(), route.toString(), Toast.LENGTH_LONG).show();
                    intent.putExtra(Const.SEARCH_ROUTE_KEY, route);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        //Route route = getIntent().getParcelableExtra(Const.POSITION_KEY);
        /*if(route!=null){
            ok = 1;
            etFrom.setText(route.getDepart());
            etDestination.setText(route.getDestination());
            etDate.setText((CharSequence) route.getDate());
        }*/
    }

    private void initView() {
        etFrom = findViewById(R.id.search_route_et_from);
        etDestination = findViewById(R.id.search_route_et_to);
        etDate = findViewById(R.id.search_route_et_date);

        spinType = findViewById(R.id.search_route_spn_type);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.search_route_type, R.layout.support_simple_spinner_dropdown_item);
        spinType.setAdapter(spinnerAdapter);

        rgLenght = findViewById(R.id.search_route_rg_shortest_route);
        rbYes = findViewById(R.id.search_route_rb_yes);
        rbNo = findViewById(R.id.search_route_rb_no);

        btnSearch = findViewById(R.id.search_route_btn_search);
    }

    private Route createSearchedRoute(){
        String rFrom = etFrom.getText().toString();
        String rTo = etDestination.getText().toString();
        Date rDate = null;
        try {
            rDate = new SimpleDateFormat(MY_DATE_FORMAT, Locale.CANADA).parse(etDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String rType = spinType.getSelectedItem().toString();
        RadioButton shortest = findViewById(rgLenght.getCheckedRadioButtonId());
        String sRoute = shortest.getText().toString();

        return new Route(rFrom, rTo, rDate, rType, sRoute);
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
        if(etDate.getText() == null || etDate.getText().toString().trim().isEmpty() || !dateValidation(etDate.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Data trebuie sa aiba formatul: dd.MM.yyyy", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean dateValidation(String stringDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MY_DATE_FORMAT, Locale.CANADA);
        try {
            simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
