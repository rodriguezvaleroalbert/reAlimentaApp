package cat.copernic.rodriguez.albert.m7t1.apartats_donant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Oferta;

public class MiraOfertesPropiesFragment extends Fragment {

    private ArrayList<Oferta> mOfertesData;
    private MiraOfertesPropiesAdapter mAdapter;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_miraofertespropies, container, false);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //Inicialitzar el RecyclerView
        //Variables membres pel RecyclerView i l'adaptador
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);

        //Establir el LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Inicialitzar l'ArrayList que contendrà les dades
        mOfertesData = new ArrayList<>();

        //Inicialitzar l'adaptador i establir-lo al RecyclerView
        mAdapter = new MiraOfertesPropiesAdapter(getContext(), mOfertesData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData(database);
        return root;
    }

    private void initializeData(FirebaseDatabase database) {
        mOfertesData.clear();
        DatabaseReference DROferta = database.getReference("Ofertes/" + mAuth.getUid());
        DROferta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotAux : dataSnapshot.getChildren()) {
                    Oferta nova = new Oferta();
                    String descripcioOferta = (String) dataSnapshotAux.child("descripcioOferta").getValue();
                    nova.setDescripcioOferta(descripcioOferta);
                    String horariRecogida = (String) dataSnapshotAux.child("horariRecogida").getValue();
                    nova.setHorariRecogida(horariRecogida);
                    if (dataSnapshotAux.child("idOferta").getValue() != null) {
                        long idOferta = (long) dataSnapshotAux.child("idOferta").getValue();
                        System.out.println("El long: " + idOferta);
                        nova.setIdOferta((int) idOferta);
                    }
                    else {
                        System.out.println("Baia");
                    }
                    String titolOferta = (String) dataSnapshotAux.child("titolOferta").getValue();
                    nova.setTitolOferta(titolOferta);
                    String idNegoci = (String) dataSnapshotAux.child("idNegoci").getValue();
                    nova.setIdNegoci(idNegoci);
                    String ubicacioNegoci = (String) dataSnapshotAux.child("ubicacioNegoci").getValue();
                    nova.setUbicacioNegoci(ubicacioNegoci);
                    mOfertesData.add(nova);

                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}