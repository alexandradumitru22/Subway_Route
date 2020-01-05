package eu.ase.ro.dam.subway_route.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.util_class.Feedback;
import eu.ase.ro.dam.subway_route.util_class.ProfilPicture;
import eu.ase.ro.dam.subway_route.util_class.Route;
import eu.ase.ro.dam.subway_route.util_interface.Const;

import static java.lang.String.valueOf;

public class ProfileActivity extends AppCompatActivity {
    public List<Route> routes = new ArrayList<>();
    private ImageButton ibtnUpload;
    private Button btnExit;
    private MenuItem itemLogin;
    private MenuItem itemRegister;
    private TextView starNumber;
    private TextView userConnected;
    private SharedPreferences sharedPreferences;
    private Feedback feedback;
    private TextView toChart;
    private ImageView profilePicture;

    private ImageButton manButton;
    private ImageButton femaleButton;

    private DatabaseReference databaseReferenceFeedback;
    private DatabaseReference databaseReferenceRoute;
    private DatabaseReference databaseReferenceProfil;

    private String manURL = "https://previews.123rf.com/images/jemastock/jemastock1705/jemastock170501669/77402307-color-pencil-front-face-caricature-old-man-with-moustache-vector-illustration.jpg";
    private String womanURL = "https://previews.123rf.com/images/grgroup/grgroup1704/grgroup170403176/76734850-color-pencil-drawing-of-caricature-half-body-girl-with-red-long-hair-vector-illustration.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();

        getProfileImageFromFirebase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        itemRegister = menu.findItem(R.id.item_register);
        itemLogin= menu.findItem(R.id.item_login);

        itemRegister.setVisible(false);
        itemLogin.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.item_register:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.item_login:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.item_search_route:
                intent = new Intent(getApplicationContext(), SearchRouteActivity.class);
                startActivityForResult(intent, Const.SEARCH_ROUTE_CODE);
                break;
            case R.id.item_routes:
                intent = new Intent(getApplicationContext(), RoutesActivity.class);
                intent.putParcelableArrayListExtra(Const.ROUTES_KEY, (ArrayList<Route>)routes);
                startActivityForResult(intent,Const.ROUTES_CODE);
                break;
            case R.id.item_info:
                intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.item_feedback:
                intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivityForResult(intent, Const.FEEDBACK_CODE);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK && data != null) && (requestCode == Const.SEARCH_ROUTE_CODE)) {
            Route route = data.getParcelableExtra(Const.SEARCH_ROUTE_KEY);
            if (route != null) {
                routes.add(route);

                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                route.setUsername(user);

                databaseReferenceRoute.setValue(routes);
            }
            else {
                Toast.makeText(getApplicationContext(), R.string.errsavef, Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == Const.FEEDBACK_CODE && resultCode == RESULT_OK && data!=null){
            feedback = data.getParcelableExtra(Const.FEEDBACK_KEY);
            if(feedback != null){
                Toast.makeText(getApplicationContext(), R.string.savef, Toast.LENGTH_LONG).show();

                String nota = valueOf(feedback.getNota());
                starNumber.setText(nota);
                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                feedback.setUsername(user);

                databaseReferenceFeedback.setValue(feedback);
            }
            else {
                Toast.makeText(getApplicationContext(), R.string.errsavef, Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == Const.REQ_CODE_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            Uri imageUri = data.getData();
            profilePicture.setImageURI(imageUri);
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getMarkFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child(getString(R.string.cf)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String user = child.getValue(Feedback.class).getUsername();
                    String comm = child.getValue(Feedback.class).getComentariu();
                    float mark = child.getValue(Feedback.class).getNota();

                    Feedback f = new Feedback();

                    f.setUsername(user);
                    f.setNota(mark);
                    f.setComentariu(comm);

                    if(user.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        starNumber.setText(valueOf(mark));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getProfileImageFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child(getString(R.string.cp)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    String user = child.getValue(ProfilPicture.class).getUser();
                    String URL = child.getValue(ProfilPicture.class).getURL();

                    ProfilPicture pp = new ProfilPicture();

                    pp.setUser(user);
                    pp.setURL(URL);

                    if(user.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        Picasso.get().load(URL).into(profilePicture);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        routes = new ArrayList<>();
        profilePicture = findViewById(R.id.profilePicture);
        btnExit = findViewById(R.id.profile_btn_deconectare);
        ibtnUpload = findViewById(R.id.profile_ibtn_upload);
        starNumber = findViewById(R.id.profile_tv_mark);
        userConnected = findViewById(R.id.profile_tv_user);
        toChart = findViewById(R.id.tv_profile_chart);
        manButton = findViewById(R.id.ib_male);
        femaleButton = findViewById(R.id.ib_female);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReferenceFeedback = database.getReference("feedback").push();
        databaseReferenceRoute = database.getReference("rute").push();
        databaseReferenceProfil = database.getReference("profile").push();

        getMarkFromFirebase();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.info_deconectare, Toast.LENGTH_LONG).show();
                sharedPreferences = getSharedPreferences(Const.SHARED_PREF_LOG, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Const.SP_MAIL_KEY);
                editor.remove(Const.SP_PASS_KEY);
                editor.apply();

                sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= sharedPreferences.edit();
                editor1.remove(Const.SHARED_PREF_RATING_KEY);
                editor1.apply();

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        toChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(i);
            }
        });

        manButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(manURL).into(profilePicture);

                ProfilPicture profilPicture = new ProfilPicture();

                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String url = manURL;
                profilPicture.setUser(user);
                profilPicture.setURL(url);

                databaseReferenceProfil.setValue(profilPicture);
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(womanURL).into(profilePicture);

                ProfilPicture profilPicture = new ProfilPicture();
                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String url = womanURL;
                profilPicture.setUser(user);
                profilPicture.setURL(url);

                databaseReferenceProfil.setValue(profilPicture);
            }
        });

        ibtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, Const.REQ_CODE_IMAGE);
            }
        });

        /*SharedPreferences mark = getSharedPreferences(Const.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        float nota = mark.getFloat(Const.SHARED_PREF_RATING_KEY, 0);
        starNumber.setText(String.valueOf(nota));*/

        SharedPreferences user = getSharedPreferences(Const.SHARED_PREF_LOG, Context.MODE_PRIVATE);
        String username = user.getString(Const.SP_MAIL_KEY, null);
        userConnected.setText(username);
    }
}
