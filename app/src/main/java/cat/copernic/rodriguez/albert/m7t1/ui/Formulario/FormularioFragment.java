package cat.copernic.rodriguez.albert.m7t1.ui.Formulario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import cat.copernic.rodriguez.albert.m7t1.R;

public class FormularioFragment extends Fragment {

    private FormularioViewModel formularioViewModel;
    private Spinner preg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        formularioViewModel = ViewModelProviders.of(this).get(FormularioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_formulario, container, false);

        preg = root.findViewById(R.id.spinner);
        String[] preguntas = {" ", "¿Vives solo?", "¿Cada cuanto se te gasta los alimentos?", "¿Llegas al fin de mes?"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, preguntas);
        preg.setAdapter(adapter);

        return root;
    }
}
