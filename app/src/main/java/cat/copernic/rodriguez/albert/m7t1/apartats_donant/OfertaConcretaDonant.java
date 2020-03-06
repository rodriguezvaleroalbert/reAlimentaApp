package cat.copernic.rodriguez.albert.m7t1.apartats_donant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cat.copernic.rodriguez.albert.m7t1.R;

public class OfertaConcretaDonant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donant_concreta_oferta);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        TextView ofertesTitle = findViewById(R.id.titol);
        TextView ofertesDescripcio = findViewById(R.id.descripcio);
        ofertesTitle.setText(getIntent().getStringExtra("titol"));
        ofertesDescripcio.setText((getIntent().getStringExtra("descripcio")));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
            //getSupportActionBar().setTitle("");
        }
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

}