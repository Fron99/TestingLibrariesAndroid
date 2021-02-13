package es.fjaviles.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelLoginActivity extends ViewModel {

    private MutableLiveData<Boolean> correctLoginRegister;
    private MutableLiveData<String> fragmentSelected;

    public ViewModelLoginActivity(){
        correctLoginRegister = new MutableLiveData<>(false);
        fragmentSelected = new MutableLiveData<>();
    }

    public LiveData<Boolean> getCorrectLoginRegister(){
        if (correctLoginRegister == null){
            correctLoginRegister = new MutableLiveData<>();
        }
        return correctLoginRegister;
    }

    public void setCorrectLoginRegister(boolean result){
        correctLoginRegister.setValue(result);
    }

    public LiveData<String> getFragmentSelected(){
        if (fragmentSelected == null){
            fragmentSelected = new MutableLiveData<>();
        }
        return fragmentSelected;
    }

    public void setFragmentSelected(String result){
        fragmentSelected.setValue(result);
    }

}
