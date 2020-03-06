package cat.copernic.rodriguez.albert.m7t1.login_registre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.copernic.rodriguez.albert.m7t1.Nav;
import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.apartats_donant.MainDonant;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    //DatabaseReference DRnomUsuari;
    DatabaseReference DRtipusUsuari;

    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        /*FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
                FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser != null) {
                    comprobaTipusUsuari(database);
                }
            }
        };*/
        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        Button mLogin = findViewById(R.id.btnLogin);
        Button mRegisterBtn = findViewById(R.id.btnRegistrar);
        //DRnomUsuari = database.getReference("Usuaris/" + mAuth.getUid() + "/mailUsuari");
        //DRtipusUsuari = database.getReference("Usuaris/" + mAuth.getUid() + "/tipusUsuari");
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = mUsername.getText().toString().trim();
                String pass = mUserpasswd.getText().toString().trim();
                identificarse(usuario, pass, database);
            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void identificarse(final String email, String password, final FirebaseDatabase database) {
        if (!mUsername.getText().toString().trim().isEmpty() && !mUserpasswd.getText().toString().trim().isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, R.string.LoginCorrecte,
                                        Toast.LENGTH_SHORT).show();
                                comprobaTipusUsuari(database);

                            } else {
                                Toast.makeText(LoginActivity.this, R.string.AutentificacióFallida,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            Toast.makeText(LoginActivity.this, "Has d'introduïr tots els camps.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void comprobaTipusUsuari(final FirebaseDatabase database) {
        final int[] tipusUsuari = new int[1];
        DRtipusUsuari = database.getReference("Usuaris/" + mAuth.getUid() + "/tipusUsuari");
        DRtipusUsuari.addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer tipusUsuariAux = dataSnapshot.getValue(Integer.class);
                if (tipusUsuariAux != null) {
                    tipusUsuari[0] = tipusUsuariAux;
                    if (tipusUsuari[0] == 2) {

                                    Intent intent = new Intent(LoginActivity.this, MainDonant.class);
                                    startActivity(intent);
                                    finish();
                    } else {
                                    Intent intent = new Intent(LoginActivity.this, Nav.class);
                                    startActivity(intent);
                                    finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));
    }
}
