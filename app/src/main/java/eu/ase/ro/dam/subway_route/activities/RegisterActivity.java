package eu.ase.ro.dam.subway_route.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.ro.dam.subway_route.R;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText et_email;
    TextInputEditText et_password;
    TextInputEditText et_confirm_password;
    Button button_register;
    Intent intent;

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
                if(infoValidation()) {
                    Toast.makeText(getApplicationContext(), R.string.info_creare_user, Toast.LENGTH_LONG).show();
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean infoValidation(){
        if(et_email.getText() == null || et_email.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_mail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_password.getText() == null || et_password.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_set_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_confirm_password.getText() == null || et_confirm_password.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.err_confirm_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
