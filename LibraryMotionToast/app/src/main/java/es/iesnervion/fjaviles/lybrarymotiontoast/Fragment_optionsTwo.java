package es.iesnervion.fjaviles.lybrarymotiontoast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.iesnervion.fjaviles.lybrarymotiontoast.databinding.FragmentOptionsOneBinding;
import es.iesnervion.fjaviles.lybrarymotiontoast.databinding.FragmentOptionsTwoBinding;
import www.sanju.motiontoast.MotionToast;


public class Fragment_optionsTwo extends Fragment {

    private FragmentOptionsTwoBinding binding;

    public Fragment_optionsTwo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOptionsTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn1.setOnClickListener(v -> {

            MotionToast.Companion.createColorToast(getActivity(),"Hurray success \uD83D\uDE0D","Upload Completed successfully!",
                    MotionToast.TOAST_SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));
        });

        binding.btn2.setOnClickListener(v -> {

            MotionToast.Companion.createColorToast(getActivity(),"Deleted!","Deleted Completed successfully!",
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));
        });

        binding.btn3.setOnClickListener(v -> {

            MotionToast.Companion.createColorToast(getActivity(),"Oh! Watch out!","You want die?",
                    MotionToast.TOAST_WARNING,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));
        });

        binding.btn4.setOnClickListener(v -> {

            MotionToast.Companion.createColorToast(getActivity(),"Oh yeah, you are failing grade","Sorry \uD83D\uDE1E \uD83D\uDE1E",
                    MotionToast.TOAST_INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));
        });

        binding.btn5.setOnClickListener(v -> {

            MotionToast.Companion.createColorToast(getActivity(),"Live Deleted!","You are eliminate",
                    MotionToast.TOAST_DELETE,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));
        });


    }
}