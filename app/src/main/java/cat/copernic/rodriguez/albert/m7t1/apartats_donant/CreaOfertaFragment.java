package cat.copernic.rodriguez.albert.m7t1.apartats_donant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Oferta;
import cat.copernic.rodriguez.albert.m7t1.login_registre.LoginActivity;

public class CreaOfertaFragment extends Fragment {
    private EditText mNom, mDescripcio, mHorari;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_creaoferta, container, false);
        mAuth = FirebaseAuth.getInstance();
        mNom = root.findViewById(R.id.etTtolOferta);
        mDescripcio = root.findViewById(R.id.etDescripcioOferta);
        mHorari = root.findViewById(R.id.etHorariOferta);
        Button puja = root.findViewById(R.id.btnPuja);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        puja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = mNom.getText().toString().trim();
                String descripcio = mDescripcio.getText().toString().trim();
                String horari = mHorari.getText().toString().trim();
                penjaOferta(nom, descripcio, horari, database);
            }
        });
        return root;
    }

    private void penjaOferta(final String nom, final String descripcio, final String horari, final FirebaseDatabase database) {
        final DatabaseReference DRllistaOfertes = database.getReference("Ofertes/" + mAuth.getUid());
        DRllistaOfertes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Oferta> llistaOfertes = (ArrayList<Oferta>) dataSnapshot.getValue();
                if (llistaOfertes != null) {
                    int quantitat = llistaOfertes.size();
                    final DatabaseReference DRPujaOferta = database.getReference("/Ofertes" + "/" + mAuth.getUid() + "/" + quantitat);
                    Oferta novaOferta = new Oferta(quantitat, nom, descripcio, horari);
                    DRPujaOferta.setValue(novaOferta);
                    Toast.makeText(getActivity().getApplicationContext(), "Pujat.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    int quantitat = 0;
                    final DatabaseReference DRPujaOferta = database.getReference("/Ofertes" + "/" + mAuth.getUid()+ "/"+quantitat);
                    Oferta novaOferta = new Oferta(quantitat, nom, descripcio, horari);
                    DRPujaOferta.setValue(novaOferta);
                    Toast.makeText(getActivity().getApplicationContext(), "Pujat.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}