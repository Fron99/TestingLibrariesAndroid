package es.iesnervion.fjaviles.libraryanimatedbottombar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import es.iesnervion.fjaviles.libraryanimatedbottombar.databinding.ActivityMainBinding;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private AnimatedBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = findViewById(R.id.bottom_bar);

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {

                String seleccionado = bottomBar.getSelectedTab().getTitle();

                switch (seleccionado){

                    case "Home":

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentInformacion, FragmentHome.class, null)
                                .addToBackStack("1").commit();

                        break;

                    case "Person":

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentInformacion, FragmentPersona.class, null)
                                .addToBackStack("1").commit();

                        break;

                }

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });


    }
}