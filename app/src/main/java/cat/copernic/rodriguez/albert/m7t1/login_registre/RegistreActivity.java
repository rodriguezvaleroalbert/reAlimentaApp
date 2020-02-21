package cat.copernic.rodriguez.albert.m7t1.login_registre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import cat.copernic.rodriguez.albert.m7t1.R;

public class RegistreActivity extends AppCompatActivity {
    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;
    private  Button mRegisterBtn;

    //Variable de los datos que vamos a registrar
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //Esto es del Shared Preferens
    /*
    //Creamos las variables para los Strings
    String Name, Password;
    //Creamos la variable de preferencias
    private SharedPreferences mSharedPreferences;

    //Valors posibles del mLogin
    public static final String PREFERENCE = "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    //public static final String PREF_SKIP_LOGIN = "skip_login"; */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        mRegisterBtn = (Button) findViewById(R.id.btnRegistrar);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mUsername.getText().toString();
                password = mUserpasswd.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    if (password.length() >=6){
                        registerUser();
                    }
                    else {
                        Toast.makeText(RegistreActivity.this,"El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT);
                    }
                }
            }
        });


        //Esto es del Shared Preferenc no nos sirve ahora
        /*
        //Crea un objecto de preferencias. ("Datos", es el nombre del archivo de preferencias, "MODE_PRIVATE", es para que otras aplicaciones no puedan acceder al archivo XML de preferencias)
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE); //O getDefaultSharedPreferences(getApplicationContext()

        //Fer registre
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (validUserData()) {
                    if (isEmailValid(Name)) {
                        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                        mEditor.putString(PREF_NAME, String.valueOf(Name));
                        mEditor.putString(PREF_PASSWD, String.valueOf(Password));
                        mEditor.apply();
                        Toast.makeText(getApplicationContext(), R.string.regCorrrecte, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistreActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.notEmail, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.buit, Toast.LENGTH_SHORT).show();
                }
            }
        }); */


    }
        //Esto tampoco sirve por que es del shared Preferencis
        /*
    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(mUsername.getText().toString().isEmpty() || mUserpasswd.getText().toString().isEmpty());
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    } */
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();
                        map.put("email", email );
                        map.put("password", password );
                        String id = mAuth.getCurrentUser().getUid();

                        mDatabase.child("Usuario ").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()){
                                    startActivity(new Intent( RegistreActivity.this,LoginActivity.class ));
                                    finish();
                                }
                                else { //11:45 / 20:52
                                    Toast.makeText(RegistreActivity.this, "No se pudieron crear los datos correctamente.",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                    else { //11:45 / 20:52
                        Toast.makeText(RegistreActivity.this, "No se puede registrar este usuario",Toast.LENGTH_SHORT).show();

                    }
            }
        });

        }
    }


