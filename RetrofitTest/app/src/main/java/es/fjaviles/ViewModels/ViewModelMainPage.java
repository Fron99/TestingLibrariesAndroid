package es.fjaviles.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.fjaviles.ApiRest.Model.Person;

public class ViewModelMainPage extends ViewModel {

    private ArrayList<Person> persons;
    private Person personSelected;
    private MutableLiveData<String> fragmentSelected;

    public ViewModelMainPage(){
        persons = new ArrayList<>();
        fragmentSelected = new MutableLiveData<>();
    }

    public ViewModelMainPage(ArrayList<Person> persons){
        persons = new ArrayList<>(persons);
        fragmentSelected = new MutableLiveData<>();
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    public void addPersons(ArrayList<Person> persons){
        this.persons.clear();
        this.persons.addAll(persons);
    }

    public void removePerson(Person person){
        persons.remove(person);
    }

    public void removePerson(int positionPerson){
        persons.remove(positionPerson);
    }

    public Person getPerson(int positionPerson){
        return persons.get(positionPerson);
    }

    public ArrayList<Person> getPersons(){
        return (ArrayList<Person>) persons.clone();
    }

    public LiveData<String> getFragmentSelected(){
        if (fragmentSelected == null){
            fragmentSelected = new MutableLiveData<>();
        }
        return fragmentSelected;
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

}
