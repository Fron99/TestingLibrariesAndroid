package es.fjaviles.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import es.fjaviles.R;
import es.fjaviles.Utils.InfoUsers;
import es.fjaviles.ViewModels.ViewModelLoginActivity;
import es.fjaviles.ViewModels.ViewModelMainPage;
import es.fjaviles.databinding.FragmentLoginBinding;
import www.sanju.motiontoast.MotionToast;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private ViewModelLoginActivity VMLoginActivity;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VMLoginActivity = new ViewModelProvider(requireActivity()).get(ViewModelLoginActivity.class);

        binding.btnRegister.setOnClickListener(v -> {
            VMLoginActivity.setFragmentSelected("Register");
        });


        binding.btnLogin.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);

            String email = binding.edtTextEmail.getText().toString();
            String password = binding.edtTextPassword.getText().toString();

            if (password.length()>=6){

                if (email.contains(".com") || email.contains(".es")){

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                            email,password).
                            addOnCompleteListener(command -> {
                                if (command.isSuccessful()){
                                    VMLoginActivity.setCorrectLoginRegister(true);

                                    MotionToast.Companion.createToast(requireActivity(),
                                            "Inicio correcto", "Ha iniciado como: "+email,
                                            MotionToast.TOAST_INFO,
                                            MotionToast.GRAVITY_BOTTOM,
                                            MotionToast.LONG_DURATION,
                                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));

                                }else{

                                    MotionToast.Companion.createToast(requireActivity(),
                                            "Error", "Introduzca otro email",
                                            MotionToast.TOAST_ERROR,
                                            MotionToast.GRAVITY_BOTTOM,
                                            MotionToast.LONG_DURATION,
                                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));
                                }
                            });

                }else{
                    MotionToast.Companion.createToast(requireActivity(),
                            "Error", "Email no valido",
                            MotionToast.TOAST_ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));
                }


            }else{
                MotionToast.Companion.createToast(requireActivity(),
                        "Error", "Contraseña demasiado corta",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));
            }

        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}