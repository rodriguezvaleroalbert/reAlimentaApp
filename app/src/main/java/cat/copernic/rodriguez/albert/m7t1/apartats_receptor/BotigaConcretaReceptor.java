package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cat.copernic.rodriguez.albert.m7t1.R;

public class BotigaConcretaReceptor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concreta_botiga);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        TextView botigaTitle = findViewById(R.id.titol);
        TextView botigaDescripcio = findViewById(R.id.descripcio);
        TextView botigaUbicacio = findViewById(R.id.txtUbicacioNegoci);
        String nomNegoci = getIntent().getStringExtra("nomNegoci");
        String descripcioNegoci = (getIntent().getStringExtra("descripcioNegoci"));
        String ubicacioNegoci = (getIntent().getStringExtra("ubicacioNegoci"));
        //String idUsuari = getIntent().getStringExtra("idUsuari", 0);
        //int idNegoci = getIntent().getIntExtra("idNegoci", 0);
        botigaTitle.setText(nomNegoci);
        botigaDescripcio.setText(descripcioNegoci);
        botigaUbicacio.setText(ubicacioNegoci);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
            //getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(BotigaConcretaReceptor.this, Nav.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}