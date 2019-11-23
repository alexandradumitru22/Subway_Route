package eu.ase.ro.dam.subway_route;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.google.android.material.textfield.TextInputEditText;

public class FeedbackActivity extends AppCompatActivity {
    TextInputEditText comment;
    RatingBar rtb_feedback;
    ImageButton ibtn_save;

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
    }
}
