package es.iesnervion.fjaviles.lybrarymotiontoast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.DialogInterface;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import es.iesnervion.fjaviles.lybrarymotiontoast.databinding.ActivityMainBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AnimatedBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(this.getSupportActionBar()).hide();
        bottomBar = findViewById(R.id.bottom_bar);

        chargeDefaultFragment();
        chargeActionsBottomBar();

    }

    public void chargeActionsBottomBar(){

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {

                switch (bottomBar.getSelectedIndex()){

                    case 0:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentOptions, Fragment_optionsOne.class, null)
                                .commit();

                        break;

                    case 1:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentOptions, Fragment_optionsTwo.class, null)
                                .commit();

                        break;

                    case 2:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentOptions, Fragment_optionsThree.class, null)
                                .commit();

                        break;

                    case 3:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentOptions, Fragment_optionsFour.class, null)
                                .commit();

                        break;

                }

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }

    public void chargeDefaultFragment(){

        switch (bottomBar.getSelectedIndex()){

            case 0:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentOptions, Fragment_optionsOne.class, null).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentOptions, Fragment_optionsTwo.class, null).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentOptions, Fragment_optionsThree.class, null).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentOptions, Fragment_optionsFour.class, null).commit();
                break;
        }

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}