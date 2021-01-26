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

        dialogLoading = new DialogLoading(this);
        dialogLoading.startLoadingDialog();

        VMMainPage = new ViewModelProvider(this).get(ViewModelMainPage.class);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {

                switch (s){

                    case "FragmentListPersons":
                        getSupportFragmentManager().popBackStack("FragmentDetailsPersons", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

                }

            }
        };


        VMMainPage.getFragmentSelected().observe(this,observer);

        chargePersons();

    }

    private void chargePersons(){
        Call<ArrayList<Person>> callFillPersons = ApiAdapter.getApiService().getPersons();
        callFillPersons.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                if(response.isSuccessful()){
                    dialogLoading.stopLoadingDialog();
                    VMMainPage.addPersons(response.body());
                    if (VMMainPage.getPersons().size() > 0){
                        VMMainPage.changeFragmentSelected("FragmentListPersons");
                    }
                }else{
                    onFailure(call,new Throwable("Parse error"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {

                dialogLoading.stopLoadingDialog();
                MotionToast.Companion.createToast(getParent(),"ERROR","The person not deleted",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getApplication(),R.font.helvetica_regular));

            }
        });
    }

}