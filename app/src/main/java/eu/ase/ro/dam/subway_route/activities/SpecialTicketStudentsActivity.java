package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class SpecialTicketStudentsActivity extends AppCompatActivity {
    private static final String url = "https://api.myjson.com/bins/o91qe";
    private HttpResponse httpResponse;

    private ListView lvStudentCounter;
    private ArrayList<SpecialTicket> studResults = new ArrayList<>();

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_ticket_students);

        initView();

        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), "Se preia lista... ", Toast.LENGTH_SHORT).show();
                httpResponse= JsonParser.parseJson(s);
                if(httpResponse !=null){
                    Toast.makeText(getApplicationContext(), "Lista statiilor preluata cu succes", Toast.LENGTH_SHORT).show();
                }
                if (httpResponse != null && httpResponse.getEleviStudenti() != null){
                    selectStudStation(httpResponse.getEleviStudenti());
                }
            }
        }.execute(url);
    }

    private void initView(){
        lvStudentCounter = findViewById(R.id.stfs_lv_case_stud);

        if(getApplicationContext() != null){
            StationAdapter stationAdapter = new StationAdapter(getApplicationContext(), R.layout.station_program_adapter, studResults, getLayoutInflater());
            lvStudentCounter.setAdapter(stationAdapter);
        }
    }

    private void selectStudStation(List<SpecialTicket> list){
        studResults.clear();
        studResults.addAll(list);
        ArrayAdapter<SpecialTicket> adapter = (ArrayAdapter<SpecialTicket>) lvStudentCounter.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
