package cat.copernic.rodriguez.albert.m7t1.login_registre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Negoci;

public class CreaNegoci extends AppCompatActivity {
    EditText mNom, mDescripcio, mUbicacio;
    Button mRegisterBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negoci_crea);
        mAuth = FirebaseAuth.getInstance();
        mNom = findViewById(R.id.txtNomNegoci);
        mDescripcio = findViewById(R.id.txtDescripcioNegoci);
        mUbicacio = findViewById(R.id.txtUbicacioNegoci);
        mRegisterBtn = findViewById(R.id.btnRegistrar);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //S'inicia tipusUsuari perquè no doni error.
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = mNom.getText().toString().trim();
                String descripcio = mDescripcio.getText().toString().trim();
                String ubicacio = mUbicacio.getText().toString().trim();
                crearNegoci(nom, descripcio, ubicacio, database);
            }
        });
    }

    private void crearNegoci(final String nom, final String descripcio, final String ubicacio, final FirebaseDatabase database) {
        if (!mNom.getText().toString().trim().isEmpty() && !mDescripcio.getText().toString().trim().isEmpty() && !mUbicacio.getText().toString().trim().isEmpty()) {
            DatabaseReference infoNegoci = database.getReference("Negocis/" + mAuth.getUid());
            infoNegoci.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        int posicio = (int) dataSnapshot.getChildrenCount();
                        DatabaseReference DRPujaNegoci = database.getReference("/Negocis" + "/" + mAuth.getUid() + "/" + posicio);
                        Negoci nouNegoci = new Negoci(posicio, nom, descripcio, ubicacio, mAuth.getUid());
                        DRPujaNegoci.setValue(nouNegoci);
                        Toast.makeText(CreaNegoci.this, R.string.registreBotiga,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        int posicio = 0;
                        DatabaseReference DRPujaNegoci = database.getReference("/Negocis" + "/" + mAuth.getUid() + "/" + posicio);
                        Negoci nouNegoci = new Negoci(posicio, nom, descripcio, ubicacio, mAuth.getUid());
                        DRPujaNegoci.setValue(nouNegoci);
                        Toast.makeText(CreaNegoci.this, R.string.registre_botiga_correcte,
                                Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(CreaNegoci.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            Toast.makeText(CreaNegoci.this, R.string.introduirTotsElsCamps,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
