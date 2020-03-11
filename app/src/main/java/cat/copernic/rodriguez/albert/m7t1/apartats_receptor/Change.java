package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cat.copernic.rodriguez.albert.m7t1.R;

public class Change extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.change, container, false);
        Button btnSigna = root.findViewById(R.id.btnSigna);
        btnSigna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.change.org/p/comisi%C3%B3n-europea-obliguen-a-los-supermercados-a-donar-toda-la-comida-que-le-sobra-a-entidades-sociales-y-ongs-lacomidanosetira"));
                startActivity(browserIntent);
            }
        });
        return root;
    }
}
