package es.fjaviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import es.fjaviles.ApiRest.ApiAdapter;
import es.fjaviles.ApiRest.Model.Person;
import es.fjaviles.Fragments.FragmentCreatePerson;
import es.fjaviles.Fragments.FragmentDetailsPersons;
import es.fjaviles.Fragments.FragmentEditPerson;
import es.fjaviles.Fragments.FragmentListPersons;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;

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
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .addToBackStack("FragmentCreatePerson")
                                .replace(R.id.fragmentTotal, FragmentCreatePerson.class, null)
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