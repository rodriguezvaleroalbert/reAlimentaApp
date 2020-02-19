package cat.copernic.rodriguez.albert.m7t1.apartats_receptor.botigues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cat.copernic.rodriguez.albert.m7t1.R;

public class BotiguesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_botigues, container, false);
    }
}