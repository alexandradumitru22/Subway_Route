package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Feedback;

public class ChartActivity extends AppCompatActivity {

    List<Feedback> fs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        getFeedbackFromFirebase();

    }

    private void getFeedbackFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child("feedback").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String user = child.getValue(Feedback.class).getUsername();
                    String comm = child.getValue(Feedback.class).getComentariu();
                    float mark = child.getValue(Feedback.class).getNota();

                    Feedback f = new Feedback();

                    f.setUsername(user);
                    f.setNota(mark);
                    f.setComentariu(comm);

                    fs.add(f);

                    int m1 = 0;
                    int m15 = 0;
                    int m2 = 0;
                    int m25 = 0;
                    int m3 = 0;
                    int m35 = 0;
                    int m4 = 0;
                    int m45 = 0;
                    int m5 = 0;

                    for (Feedback feedback:fs){
                        if(feedback.getNota() == 1){
                            m1++;
                        }
                        else if(feedback.getNota() == 1.5){
                            m15++;
                        }
                        else if(feedback.getNota() == 2){
                            m2++;
                        }
                        else if(feedback.getNota() == 2.5){
                            m25++;
                        }
                        else if(feedback.getNota() == 3){
                            m3++;
                        }
                        else if(feedback.getNota() == 3.5){
                            m35++;
                        }
                        else if(feedback.getNota() == 4){
                            m4++;
                        }
                        else if(feedback.getNota() == 4.5){
                            m45++;
                        }
                        else if(feedback.getNota() == 5) {
                            m5++;
                        }
                    }

                    Pie pie = AnyChart.pie();

                    List<DataEntry> data = new ArrayList<>();

                    data.add(new ValueDataEntry("1 stea", m1));
                    data.add(new ValueDataEntry("1.5 stele", m15));
                    data.add(new ValueDataEntry("2 stele", m2));
                    data.add(new ValueDataEntry("2.5 stele", m25));
                    data.add(new ValueDataEntry("3 stele", m3));
                    data.add(new ValueDataEntry("3.5 stele", m35));
                    data.add(new ValueDataEntry("4 stele", m4));
                    data.add(new ValueDataEntry("4.5 stele", m45));
                    data.add(new ValueDataEntry("5 stele", m5));

                    pie.setData(data);

                    AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                    anyChartView.setChart(pie);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
