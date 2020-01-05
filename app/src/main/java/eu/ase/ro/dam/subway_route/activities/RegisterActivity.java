package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import eu.ase.ro.dam.subway_route.R;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText et_email;
    TextInputEditText et_password;
    TextInputEditText et_confirm_password;
    Button button_register;
    Intent intent;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoValidation()) {
                    auth();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

        }
    }

    private void auth(){
        final String email = et_email.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), R.string.info_creare_user, Toast.LENGTH_LONG).show();
                            intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // autentificare nereusita
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), R.string.errSameUser, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), R.string.errAuth, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private boolean infoValidation(){
        if(et_email.getText() == null || et_email.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.err_mail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString().trim()).matches()){
            Toast.makeText(getApplicationContext(), R.string.errValidEmail, Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_password.getText() == null || et_password.getText().toString().trim().isEmpty() || et_password.getText().toString().trim().length()<7){
            Toast.makeText(getApplicationContext(), R.string.err_set_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        if(et_confirm_password.getText() == null || et_confirm_password.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.err_confirm_pass, Toast.LENGTH_LONG).show();
            return false;
        }
        if(!et_confirm_password.getText().toString().trim().matches(et_password.getText().toString().trim())){
            Toast.makeText(getApplicationContext(), R.string.errSamePass, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
