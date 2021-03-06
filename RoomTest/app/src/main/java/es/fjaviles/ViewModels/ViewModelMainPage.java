package es.fjaviles.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.fjaviles.Dao.Model.Person;

public class ViewModelMainPage extends ViewModel {

    private MutableLiveData<ArrayList<Person>> persons;
    private Person personSelected;
    private MutableLiveData<String> fragmentSelected;
    private MutableLiveData<String> txtToSearch;

    public ViewModelMainPage(){
        this.persons = new MutableLiveData<>(new ArrayList<>());
        this.fragmentSelected = new MutableLiveData<>();
        this.txtToSearch = new MutableLiveData<>();
    }

    public ViewModelMainPage(ArrayList<Person> persons){
        this.persons = new MutableLiveData<>(persons);
        this.fragmentSelected = new MutableLiveData<>();
        this.txtToSearch = new MutableLiveData<>();
    }

    public void addPerson(Person person){
        persons.getValue().add(person);
    }

    public void addPersons(ArrayList<Person> persons){
        this.persons.getValue().clear();
        if (persons != null)
            this.persons.setValue(persons);
    }

    public void removePerson(Person person){
        persons.getValue().remove(person);
    }

    public void removePerson(int positionPerson){
        persons.getValue().remove(positionPerson);
    }

    public Person getPerson(int positionPerson){
        return persons.getValue().get(positionPerson);
    }

    public ArrayList<Person> getPersons(){
        if ( persons.getValue() == null){
            persons = new MutableLiveData<>();
            persons.setValue(new ArrayList<>());
        }
        return (ArrayList<Person>) persons.getValue().clone();
    }

    public LiveData<String> getFragmentSelected(){
        if (fragmentSelected == null){
            fragmentSelected = new MutableLiveData<>();
        }
        return fragmentSelected;
    }

    public LiveData<ArrayList<Person>> getLiveDataPersons(){
        if (persons == null){
            persons = new MutableLiveData<>();
        }
        return persons;
    }

    public String getValueFragmentSelected(){
        if (fragmentSelected == null){
            fragmentSelected = new MutableLiveData<>();
        }
        return fragmentSelected.getValue();
    }

    public void changeFragmentSelected(String newValue){
        if (fragmentSelected == null){
            fragmentSelected = new MutableLiveData<>();
        }
        fragmentSelected.setValue(newValue);
    }

    public void setPersonSelected(Person newPersonSelected){
        this.personSelected = newPersonSelected;
    }

    public Person getPersonSelected(){
        return this.personSelected;
    }

    public LiveData<String> getTxtToSearch(){
        if (txtToSearch == null){
            txtToSearch = new MutableLiveData<>();
        }
        return txtToSearch;
    }

    public String getTxtToSearchValue(){
        if (txtToSearch == null){
            txtToSearch = new MutableLiveData<>();
        }
        return txtToSearch.getValue();
    }

    public void setTxtToSearch(String value){
        if (txtToSearch == null){
            txtToSearch = new MutableLiveData<>();
        }
        txtToSearch.setValue(value);
    }

}
