package cat.copernic.rodriguez.albert.m7t1.ui.home;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

import cat.copernic.rodriguez.albert.m7t1.Indret;
import cat.copernic.rodriguez.albert.m7t1.IndretsAdapter;
import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.WordListAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    //LLista pel RecycledView
    private final LinkedList<String> mWordList = new LinkedList<>();

    //Variables membres pel RecyclerView i l'adaptador
    private RecyclerView mRecyclerView;
    private ArrayList<Indret> mIndretsData;
    private IndretsAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //Inicialitzar el RecyclerView
        mRecyclerView = root.findViewById(R.id.recyclerView);

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