package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Negoci;

public class BotiguesFragment extends Fragment {

    private ArrayList<Negoci> mBotiguesData;
    private BotiguesAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_botigues, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Inicialitzar el RecyclerView
        //Variables membres pel RecyclerView i l'adaptador
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);

        //Establir el LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Inicialitzar l'ArrayList que contendrà les dades
        mBotiguesData = new ArrayList<>();

        //Inicialitzar l'adaptador i establir-lo al RecyclerView
        mAdapter = new BotiguesAdapter(getContext(), mBotiguesData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData(database);
        return root;
    }

    private void initializeData(FirebaseDatabase database) {
        mBotiguesData.clear();
        DatabaseReference DRUsu = database.getReference("Negocis/");
        DRUsu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotAuxUsu : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshotAux : dataSnapshotAuxUsu.getChildren()) {
                        Negoci nouNegoci = new Negoci();
                        String nomNegoci = (String) dataSnapshotAux.child("nomNegoci").getValue();
                        nouNegoci.setNomNegoci(nomNegoci);
                        String descripcioNegoci = (String) dataSnapshotAux.child("descripcioNegoci").getValue();
                        nouNegoci.setDescripcioNegoci(descripcioNegoci);
                        String ubicacioNegoci = (String) dataSnapshotAux.child("ubicacioNegoci").getValue();
                        nouNegoci.setUbicacioNegoci(ubicacioNegoci);
                        if (dataSnapshotAux.child("idNegoci").getValue() != null) {
                            long idNegoci = (long) dataSnapshotAux.child("idNegoci").getValue();
                            nouNegoci.setIdNegoci((int) idNegoci);
                        }
                        String idUsuari = (String) dataSnapshotAux.child("idUsuari").getValue();
                        nouNegoci.setIdUsuari(idUsuari);
                        mBotiguesData.add(nouNegoci);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
