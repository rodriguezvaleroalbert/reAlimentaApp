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
    //Probañ
    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;
    private Button mLogin, mRegisterBtn;

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
    public static final String PREF_SKIP_LOGIN = "skip_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        mLogin = findViewById(R.id.btnLogin);
        mRegisterBtn = findViewById(R.id.btnRegistrar);

        //Crea un objecto de preferencias. ("Datos", es el nombre del archivo de preferencias, "MODE_PRIVATE", es para que otras aplicaciones no puedan acceder al archivo XML de preferencias)
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE); //O getDefaultSharedPreferences(getApplicationContext()


        //Saltarte login
        if (mSharedPreferences.contains(PREF_SKIP_LOGIN)) {
            Intent intent = new Intent(LoginActivity.this, Nav.class);
            startActivity(intent);
            finish();
        } else  {

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
                            Toast.makeText(getApplicationContext(), "Credencials incorrectes !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else {
                        Toast.makeText(getApplicationContext(), "ESTAA BUIIIT !!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        //Fer registre
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validUserData()) {
                //Name = mUsername.getText().toString().trim();
                //Password = mUserpasswd.getText().toString().trim();
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    mEditor.putString(PREF_NAME, String.valueOf(Name));
                    mEditor.putString(PREF_PASSWD, String.valueOf(Password));
                    mEditor.putBoolean(PREF_SKIP_LOGIN, true);
                    mEditor.apply();
                    Toast.makeText(getApplicationContext(), "REGISTRAT", Toast.LENGTH_SHORT).show();
                    //finish();
                } else {
                    Toast.makeText(getApplicationContext(), "RegBUIIIIT", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(mUsername.getText().toString().isEmpty() || mUserpasswd.getText().toString().isEmpty());
    }
}
