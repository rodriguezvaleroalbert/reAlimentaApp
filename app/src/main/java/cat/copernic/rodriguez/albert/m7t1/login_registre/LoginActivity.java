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

import cat.copernic.rodriguez.albert.m7t1.Nav;
import cat.copernic.rodriguez.albert.m7t1.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;

    //Valors posibles del mLogin

    static final String TAG = "LOGINUSUARI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        Button mLogin = findViewById(R.id.btnLogin);
        Button mRegisterBtn = findViewById(R.id.btnRegistrar);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = mUsername.getText().toString().trim();
                String pass = mUserpasswd.getText().toString().trim();
                identificarse(usuario, pass);
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

    void identificarse(String email, String password) {
        if (!mUsername.getText().toString().trim().isEmpty() && !mUserpasswd.getText().toString().trim().isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(LoginActivity.this, R.string.LoginCorrecte,
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, Nav.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
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
}
