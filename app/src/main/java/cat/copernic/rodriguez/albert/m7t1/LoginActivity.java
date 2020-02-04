package cat.copernic.rodriguez.albert.m7t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;

    //Creamos las variables para los Strings
    String Name, Password;

    //Creamos las variables para la consulta en las SharedPreferences
    String uName, uPassword;

    //Creamos la variable de preferencias
    private SharedPreferences mSharedPreferences;

    //Valors posibles del mLogin
    public static final String PREFERENCE = "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    //public static final String PREF_SKIP_LOGIN = "skip_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        Button mLogin = findViewById(R.id.btnLogin);
        Button mRegisterBtn = findViewById(R.id.btnRegistrar);

        //Crea un objecto de preferencias. ("Datos", es el nombre del archivo de preferencias, "MODE_PRIVATE", es para que otras aplicaciones no puedan acceder al archivo XML de preferencias)
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE); //O getDefaultSharedPreferences(getApplicationContext()

        //Saltarse login
        /*if (mSharedPreferences.contains(PREF_SKIP_LOGIN)) {
            Intent intent = new Intent(LoginActivity.this, Nav.class);
            startActivity(intent);
            finish();
        } else  */{

            //Fer login
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validUserData()) {
                    //Name = mUsername.getText().toString().trim();
                    //Password = mUserpasswd.getText().toString().trim();
                    if(mSharedPreferences.contains(PREF_NAME)) {
                        uName = mSharedPreferences.getString(PREF_NAME, "");
                        }
                    if(mSharedPreferences.contains(PREF_PASSWD)){
                        uPassword = mSharedPreferences.getString(PREF_PASSWD, "");
                    }
                    if(Name.equals(uName) && Password.equals(uPassword)){
                        Intent intent = new Intent(LoginActivity.this, Nav.class);
                        startActivity(intent);
                        finish();
                    } else {
                            Toast.makeText(getApplicationContext(), "Usuari i/o contrasenya incorrectes !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else {
                        Toast.makeText(getApplicationContext(), "ESTAA BUIIIT !!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        //Anar al registre
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Validar que els camps no estiguin buits
    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(mUsername.getText().toString().isEmpty() || mUserpasswd.getText().toString().isEmpty());
    }
}
