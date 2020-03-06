package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.OfertesReceptorAdapter;
import cat.copernic.rodriguez.albert.m7t1.classes.Oferta;

public class OfertesFragment extends Fragment {

    private ArrayList<Oferta> mOfertesData;
    private OfertesReceptorAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ofertes, container, false);

        //Inicialitzar el RecyclerView
        //Variables membres pel RecyclerView i l'adaptador
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);

        //Establir el LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Inicialitzar l'ArrayList que contendrà les dades
        mOfertesData = new ArrayList<>();

        //Inicialitzar l'adaptador i establir-lo al RecyclerView
        mAdapter = new OfertesReceptorAdapter(getContext(), mOfertesData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();
        return root;
    }

    private void initializeData() {
        //Obtenir les arrays de l'XML
        String[] ofertesList = getResources().getStringArray(R.array.indrets_nom);
        String[] ofertesInfo = getResources().getStringArray(R.array.indrets_info);

        //Netejar les dades existents per evitar duplicats
        mOfertesData.clear();

        //Crear l'ArrayList d'Indrets amb els títols i la informació de cada indret
        for (int i = 0; i < ofertesList.length; i++) {
            mOfertesData.add(new Oferta(ofertesList[i], ofertesInfo[i]));
        }

        //Notificar l'adaptador dels canvis
        mAdapter.notifyDataSetChanged();
    }
}