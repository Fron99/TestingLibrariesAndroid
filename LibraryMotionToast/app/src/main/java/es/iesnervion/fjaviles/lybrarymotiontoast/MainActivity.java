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

                Class classExtracted = Fragment_optionsOne.class;

                switch (bottomBar.getSelectedIndex()){

                    case 0: classExtracted = Fragment_optionsOne.class; break;

                    case 1: classExtracted = Fragment_optionsTwo.class; break;

                    case 2: classExtracted = Fragment_optionsThree.class; break;

                    case 3: classExtracted = Fragment_optionsFour.class; break;
                }

                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentOptions, classExtracted, null)
                        .commit();
            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });

    }

    public void chargeDefaultFragment(){

        Class classExtracted = Fragment_optionsOne.class;

        switch (bottomBar.getSelectedIndex()){

            case 0: classExtracted = Fragment_optionsOne.class; break;

            case 1: classExtracted = Fragment_optionsTwo.class; break;

            case 2: classExtracted = Fragment_optionsThree.class; break;

            case 3: classExtracted = Fragment_optionsFour.class; break;
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentOptions, classExtracted, null)
                .commit();
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