package cat.copernic.rodriguez.albert.m7t1.apartats_receptor.ofertes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfertesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OfertesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}