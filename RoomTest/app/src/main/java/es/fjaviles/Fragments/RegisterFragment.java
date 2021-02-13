package es.fjaviles.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import es.fjaviles.R;
import es.fjaviles.ViewModels.ViewModelLoginActivity;
import es.fjaviles.databinding.FragmentLoginBinding;
import es.fjaviles.databinding.FragmentRegisterBinding;
import www.sanju.motiontoast.MotionToast;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private ViewModelLoginActivity VMLoginActivity;

    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VMLoginActivity = new ViewModelProvider(requireActivity()).get(ViewModelLoginActivity.class);

        binding.btnRegister.setOnClickListener(v -> {

            String password = binding.edtTextPassword.getText().toString();
            String passwordConfirm = binding.edtTextPasswordConfirm.getText().toString();
            String email = binding.edtTextEmail.getText().toString();

            if (password.equals(passwordConfirm)) {

                if (password.length()>=6){

                    if (email.contains(".com") || email.contains(".es")){

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
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
                                                "Error", "Email o contraseña incorrecta",
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

            }else{

                MotionToast.Companion.createToast(requireActivity(),
                        "Error", "Las contraseñas no coinciden",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));

            }


        });


    }
}
