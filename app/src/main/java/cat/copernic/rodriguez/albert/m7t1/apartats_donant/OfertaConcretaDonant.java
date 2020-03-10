package cat.copernic.rodriguez.albert.m7t1.apartats_donant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Oferta;

public class OfertaConcretaDonant extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String titol, descripcio, horari, ubicacio;
    int posicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donant_concreta_oferta);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        final TextView ofertesTitol = findViewById(R.id.titol);
        final TextView ofertesDescripcio = findViewById(R.id.descripcio);
        final TextView ofertesHorari = findViewById(R.id.txtDescripcio);
        final TextView ofertesUbicacio = findViewById(R.id.txtUbicacioNegoci);
        Button btnModificaOferta = findViewById(R.id.modificaOferta);
        titol = getIntent().getStringExtra("titol");
        descripcio = getIntent().getStringExtra("descripcio");
        horari = getIntent().getStringExtra("horari");
        ubicacio = getIntent().getStringExtra("ubicacio");
        posicio = getIntent().getIntExtra("posicio", -1);
        System.out.println("La posici´o és: " + posicio);
        ofertesTitol.setText(titol);
        ofertesDescripcio.setText(descripcio);
        ofertesHorari.setText(horari);
        ofertesUbicacio.setText(ubicacio);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
            //getSupportActionBar().setTitle("");
        }

        btnModificaOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titol = ofertesTitol.getText().toString().trim();
                descripcio = ofertesDescripcio.getText().toString().trim();
                horari = ofertesHorari.getText().toString().trim();
                ubicacio = ofertesUbicacio.getText().toString().trim();
                modificaOferta(titol, descripcio, horari, posicio, database);
            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(OfertaConcretaDonant.this, MainDonant.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void modificaOferta(final String nom, final String descripcio, final String horari, int posicio, final FirebaseDatabase database) {
        final DatabaseReference DRPujaOferta = database.getReference("/Ofertes" + "/" + mAuth.getUid() + "/" + posicio);
        Oferta novaOferta = new Oferta(posicio, nom, descripcio, horari, mAuth.getUid(), ubicacio);
        System.out.println(novaOferta.toString());
        DRPujaOferta.setValue(novaOferta);
        Toast.makeText(OfertaConcretaDonant.this, "Pujat.",
                Toast.LENGTH_SHORT).show();
    }
}