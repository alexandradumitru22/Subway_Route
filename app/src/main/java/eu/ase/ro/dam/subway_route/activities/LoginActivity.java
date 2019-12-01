package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.R;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button btnLogin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        email = findViewById(R.id.login_et_email);
        password = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoValidation()){
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean infoValidation(){
        if(email.getText() == null || email.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_mail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText() == null || password.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
