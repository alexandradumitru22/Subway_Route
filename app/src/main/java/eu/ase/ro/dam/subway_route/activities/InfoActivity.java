package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eu.ase.ro.dam.subway_route.R;

public class InfoActivity extends AppCompatActivity {
    Button abStud;
    Button abVet;
    Button abDon;
    Button abH;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();

        abStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InfoActivity.this, SpecialTicketStudentsActivity.class);
                startActivity(intent);
            }
        });

        abVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InfoActivity.this, SpecialTicketVeteraniActivity.class);
                startActivity(intent);
            }
        });

        abDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InfoActivity.this, SpecialTicketDonatoriActivity.class);
                startActivity(intent);
            }
        });

        abH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(InfoActivity.this, SpecialTicketHandicapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        abStud = findViewById(R.id.info_btn_stud);
        abVet = findViewById(R.id.info_btn_veterani);
        abDon = findViewById(R.id.info_btn_donatori);
        abH = findViewById(R.id.info_btn_hanficap);
    }
}
