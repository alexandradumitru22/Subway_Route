package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_interface.Const;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText password;
    Button btnLogin;
    Intent intent;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        initView();
        Toast.makeText(getApplicationContext(), myEmail(), Toast.LENGTH_SHORT).show();

        if(myEmail() != null){
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }

    private void initView(){
        email = findViewById(R.id.login_et_email);
        password = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoValidation()){
                    login();
                    Toast.makeText(getApplicationContext(), myEmail(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(){
        final String mail = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Logare cu succes
                            rememberMe(mail, pass);
                            intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            //logare nereusita
                            Toast.makeText(getApplicationContext(), R.string.errLogin, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void rememberMe(String email, String password){
        sharedPreferences = getSharedPreferences(Const.SHARED_PREF_LOG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.SP_MAIL_KEY, email);
        editor.putString(Const.SP_PASS_KEY, password);
        editor.apply();
    }

    public String myEmail(){
        sharedPreferences = getSharedPreferences(Const.SHARED_PREF_LOG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Const.SP_MAIL_KEY, null);
    }

    private boolean infoValidation(){
        if(email.getText() == null || email.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_mail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
            Toast.makeText(getApplicationContext(), R.string.errValidEmail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText() == null || password.getText().toString().trim().isEmpty() || password.getText().toString().trim().length()<7){
            Toast.makeText(getApplicationContext(), R.string.err_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
