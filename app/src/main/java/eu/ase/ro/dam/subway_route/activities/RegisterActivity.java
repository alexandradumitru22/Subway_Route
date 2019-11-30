package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.R;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText et_email;
    TextInputEditText et_password;
    TextInputEditText et_confirm_password;
    Button button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView(){
        et_email = findViewById(R.id.register_et_email);
        et_password = findViewById(R.id.register_et_password);
        et_confirm_password = findViewById(R.id.register_et_repeat_password);
        button_register = findViewById(R.id.register_button);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
