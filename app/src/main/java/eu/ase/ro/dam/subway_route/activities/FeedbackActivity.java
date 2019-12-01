package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.R;

public class FeedbackActivity extends AppCompatActivity {
    TextInputEditText comment;
    RatingBar rtb_feedback;
    ImageButton ibtn_save;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
    }

    private void initView(){
        comment = findViewById(R.id.feedback_et_comment);
        rtb_feedback = findViewById(R.id.feedback_rtb_star);
        ibtn_save = findViewById(R.id.feedback_ibtn_save);

        ibtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feedback salvat", Toast.LENGTH_SHORT).show();
                intent = new Intent(FeedbackActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
