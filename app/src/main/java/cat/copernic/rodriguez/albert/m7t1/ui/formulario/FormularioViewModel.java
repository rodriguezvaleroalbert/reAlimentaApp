package cat.copernic.rodriguez.albert.m7t1.ui.formulario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormularioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FormularioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is formulario fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}