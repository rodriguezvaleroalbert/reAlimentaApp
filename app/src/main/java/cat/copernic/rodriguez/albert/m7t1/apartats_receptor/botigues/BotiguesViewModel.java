package cat.copernic.rodriguez.albert.m7t1.apartats_receptor.botigues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BotiguesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BotiguesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}