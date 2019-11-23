package eu.ase.ro.dam.subway_route;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button btnLogin;

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
    }
}
