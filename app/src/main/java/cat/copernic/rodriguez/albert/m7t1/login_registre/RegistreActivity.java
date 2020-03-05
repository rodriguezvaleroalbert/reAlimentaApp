package cat.copernic.rodriguez.albert.m7t1.login_registre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Usuari;

public class RegistreActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    Spinner spTipusUsuari;
    FirebaseAuth mAuth;
    int tipusUsuari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtPass);
        mRegisterBtn = findViewById(R.id.btnRegistrar);
        spTipusUsuari = findViewById(R.id.spTipusUsuari);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //S'inicia tipusUsuari perquè no doni error.
        agafarTipusUsuari();
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = mEmail.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                tipusUsuari = agafarTipusUsuari();
                crearUsuario(usuario, pass, tipusUsuari, database);
            }
        });
    }

    private void guardaInfoUsuario(final String usuario, final int tipusUsuari, final DatabaseReference infoUsuari) {
        Usuari nouUsuari = new Usuari(usuario, tipusUsuari);
        infoUsuari.setValue(nouUsuari);
    }

    private void crearUsuario(final String email, String password, final int tipusUsuari, final FirebaseDatabase database) {
        if (!mEmail.getText().toString().trim().isEmpty() && !mPassword.getText().toString().trim().isEmpty() && tipusUsuari != 0) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference infoUsuari = database.getReference("Usuaris/" + mAuth.getUid());
                                guardaInfoUsuario(email, tipusUsuari, infoUsuari);
                                Toast.makeText(RegistreActivity.this, R.string.RegistreCorrecte,
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistreActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegistreActivity.this, R.string.AutentificacióFallida,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else if (tipusUsuari == 0) {
            Toast.makeText(RegistreActivity.this, R.string.escollirOpcio,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegistreActivity.this, R.string.introduirTotsElsCamps,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private int agafarTipusUsuari() {
        spTipusUsuari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                tipusUsuari = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
                Toast.makeText(RegistreActivity.this, R.string.escollirOpcio,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return tipusUsuari;
    }
}
