package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.network.HttpManager;
import eu.ase.ro.dam.subway_route.network.HttpResponse;
import eu.ase.ro.dam.subway_route.network.JsonParser;
import eu.ase.ro.dam.subway_route.network.SpecialTicket;
import eu.ase.ro.dam.subway_route.util_class.StationAdapter;

public class SpecialTicketVeteraniActivity extends AppCompatActivity {
    private static final String url = "https://api.myjson.com/bins/o91qe";
    private HttpResponse httpResponse;

    private ListView lvVeteranCounter;
    private ArrayList<SpecialTicket> veteranResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_ticket_veterani);

        initView();

        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), R.string.info_preluare_json, Toast.LENGTH_SHORT).show();
                httpResponse= JsonParser.parseJson(s);
                if(httpResponse !=null){
                    Toast.makeText(getApplicationContext(), R.string.info_json_preluat, Toast.LENGTH_SHORT).show();
                }
                if (httpResponse != null && httpResponse.getVeteraniRazboi() != null){
                    selectVetStation(httpResponse.getVeteraniRazboi());
                }
            }
        }.execute(url);
    }

    private void initView(){
        lvVeteranCounter = findViewById(R.id.stfv_lv_sv);

        if(getApplicationContext() != null){
            StationAdapter stationAdapter = new StationAdapter(getApplicationContext(), R.layout.station_program_adapter, veteranResults, getLayoutInflater());
            lvVeteranCounter.setAdapter(stationAdapter);
        }
    }

    private void selectVetStation(List<SpecialTicket> list){
        veteranResults.clear();
        veteranResults.addAll(list);
        ArrayAdapter<SpecialTicket> adapter = (ArrayAdapter<SpecialTicket>) lvVeteranCounter.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
