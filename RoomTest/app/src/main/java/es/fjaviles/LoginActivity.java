package es.fjaviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import es.fjaviles.Fragments.FragmentEditPerson;
import es.fjaviles.Fragments.LoginFragment;
import es.fjaviles.Fragments.RegisterFragment;
import es.fjaviles.ViewModels.ViewModelLoginActivity;
import es.fjaviles.ViewModels.ViewModelMainPage;

public class LoginActivity extends AppCompatActivity {

    private ViewModelLoginActivity VMLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        VMLoginActivity = new ViewModelProvider(this).get(ViewModelLoginActivity.class);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentTotal, LoginFragment.class, null)
                .commit();

        asignObservers();

    }

    /**
     * Asign observers to LiveData.
     */

    private void asignObservers() {

        Observer<Boolean> changeLoginRegister = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean){
                    Intent intentToMainPage = new Intent(getApplicationContext(), MainPage.class);
                    //intentToMainPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //Es correcto?
                    finish();
                    startActivity(intentToMainPage);
                }

            }
        };

        Observer<String> goToRegister = new Observer<String>() {
            @Override
            public void onChanged(String string) {

                if (string.equals("Register")){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .addToBackStack("Register")
                            .replace(R.id.fragmentTotal, RegisterFragment.class, null)
                            .commit();
                }

            }
        };

        VMLoginActivity.getCorrectLoginRegister().observe(this,changeLoginRegister);

        VMLoginActivity.getFragmentSelected().observe(this,goToRegister);

    }

}