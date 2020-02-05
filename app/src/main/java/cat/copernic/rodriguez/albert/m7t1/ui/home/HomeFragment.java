package cat.copernic.rodriguez.albert.m7t1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.Indret;
import cat.copernic.rodriguez.albert.m7t1.IndretsAdapter;
import cat.copernic.rodriguez.albert.m7t1.R;

public class HomeFragment extends Fragment {

    private ArrayList<Indret> mIndretsData;
    private IndretsAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Inicialitzar el RecyclerView
        //Variables membres pel RecyclerView i l'adaptador
        RecyclerView mRecyclerView = root.findViewById(R.id.recyclerView);

        //Establir el LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Inicialitzar l'ArrayList que contendrà les dades
        mIndretsData = new ArrayList<>();

        //Inicialitzar l'adaptador i establir-lo al RecyclerView
        mAdapter = new IndretsAdapter(getContext(), mIndretsData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();
        return root;
    }

    private void initializeData() {
        //Obtenir les arrays de l'XML
        String[] indretsList = getResources().getStringArray(R.array.indrets_nom);
        String[] indretsInfo = getResources().getStringArray(R.array.indrets_info);

        //Netejar les dades existents per evitar duplicats
        mIndretsData.clear();

        //Crear l'ArrayList d'Indrets amb els títols i la informació de cada indret
        for (int i = 0; i < indretsList.length; i++) {
            mIndretsData.add(new Indret(indretsList[i], indretsInfo[i]));
        }

        //Notificar l'adaptador dels canvis
        mAdapter.notifyDataSetChanged();
    }
}