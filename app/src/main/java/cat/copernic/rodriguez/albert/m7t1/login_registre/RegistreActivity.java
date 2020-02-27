package cat.copernic.rodriguez.albert.m7t1.login_registre;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

import cat.copernic.rodriguez.albert.m7t1.Nav;
import cat.copernic.rodriguez.albert.m7t1.R;


public class RegistreActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtPass);
        mRegisterBtn = findViewById(R.id.btnRegistrar);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                crearUsuario(usuario, pass);
            }
        });
    }

    void crearUsuario(String email, String password) {
        if (!mEmail.getText().toString().trim().isEmpty() && !mPassword.getText().toString().trim().isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(RegistreActivity.this, R.string.RegistreCorrecte,
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistreActivity.this, Nav.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegistreActivity.this, R.string.AutentificacióFallida,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(RegistreActivity.this, "Has d'introduïr tots els camps.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
