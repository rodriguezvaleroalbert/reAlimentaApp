package cat.copernic.rodriguez.albert.m7t1.login_registre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.copernic.rodriguez.albert.m7t1.R;

public class RegistreActivity extends AppCompatActivity {
    //Creamos las variables para los textos y los botones
    private EditText mUsername, mUserpasswd;

    //Creamos las variables para los Strings
    String Name, Password;

    //Creamos la variable de preferencias
    private SharedPreferences mSharedPreferences;

    //Valors posibles del mLogin
    public static final String PREFERENCE = "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    //public static final String PREF_SKIP_LOGIN = "skip_login";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        //Se crean los findViewById para enlazar nuestras variables con los componentes del activity_logi
        mUsername = findViewById(R.id.txtEmail);
        mUserpasswd = findViewById(R.id.txtPass);
        Button mRegisterBtn = findViewById(R.id.btnRegistrar);

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
        });
    }

    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(mUsername.getText().toString().isEmpty() || mUserpasswd.getText().toString().isEmpty());
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
