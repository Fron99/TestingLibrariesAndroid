package es.fjaviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import es.fjaviles.ApiRest.Model.Person;
import es.fjaviles.Fragments.FragmentDetailsPersons;
import es.fjaviles.Fragments.FragmentEditPerson;
import es.fjaviles.Fragments.FragmentListPersons;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;

public class MainPage extends AppCompatActivity {

    private ViewModelMainPage VMMainPage;
    private DialogLoading dialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        VMMainPage = new ViewModelProvider(this).get(ViewModelMainPage.class);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {

                switch (s){

                    case "FragmentListPersons":
                        getSupportFragmentManager().popBackStack("FragmentDetailsPersons", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getSupportFragmentManager().popBackStack("FragmentCreatePerson", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getSupportFragmentManager().popBackStack("FragmentEditPerson", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentTotal, FragmentListPersons.class, null)
                                .commit();
                        break;

                    case "FragmentDetailsPersons":
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .addToBackStack("FragmentDetailsPersons")
                                .replace(R.id.fragmentTotal, FragmentDetailsPersons.class, null)
                                .commit();
                        break;

                    case "FragmentCreatePerson":
                        VMMainPage.setPersonSelected(new Person());
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .addToBackStack("FragmentEditPerson")
                                .replace(R.id.fragmentTotal, FragmentEditPerson.class, null)
                                .commit();
                        break;

                    case "FragmentEditPerson":
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .addToBackStack("FragmentEditPerson")
                                .replace(R.id.fragmentTotal, FragmentEditPerson.class, null)
                                .commit();
                        break;

                }

            }
        };


        VMMainPage.getFragmentSelected().observe(this,observer);

        VMMainPage.changeFragmentSelected("FragmentListPersons");

    }

}