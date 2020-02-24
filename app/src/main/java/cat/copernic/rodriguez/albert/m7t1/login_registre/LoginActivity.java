package cat.copernic.rodriguez.albert.m7t1.login_registre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import cat.copernic.rodriguez.albert.m7t1.Nav;
import cat.copernic.rodriguez.albert.m7t1.R;



public class LoginActivity extends AppCompatActivity {
    //Declaracion de las variables de firebase

    //Creamos las variables para los textos y los botones
    EditText mEmail, mPassword;
    Button mLoginBtn;
    Button mRegistrarBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicialisacion de la instancia del firebase
        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtPass);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.btnLogin);
        mRegistrarBtn = findViewById(R.id.btnRegistrar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Se necesita Correo.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Se necesita una contraseña.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }



                // authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Conexion correcta", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Nav.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });


        mRegistrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistreActivity.class));
            }
        });


    }
    // he de arreglar una cosa aqui .
    public void registrarUsuario(View view) {
    }
}
