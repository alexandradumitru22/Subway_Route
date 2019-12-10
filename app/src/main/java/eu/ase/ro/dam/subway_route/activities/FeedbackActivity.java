package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class FeedbackActivity extends AppCompatActivity {
    TextInputEditText comment;
    RatingBar rtb_feedback;
    ImageButton ibtn_save;
    private SharedPreferences sharedPreferences;
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

        sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        ibtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feedback salvat", Toast.LENGTH_SHORT).show();
                intent = new Intent(FeedbackActivity.this, ProfileActivity.class);

                float value = sharedPreferences.getFloat(Const.SHARED_PREF_RATING_KEY, -1);
                if(value != -1){
                    rtb_feedback.setRating(value);
                }
                intent.putExtra(Const.STAR_TRANSFER_KEY, value);
                startActivity(intent);
                finish();
            }
        });

        rtb_feedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(Const.SHARED_PREF_RATING_KEY, rating);
                editor.apply();
            }
        });
    }
}
