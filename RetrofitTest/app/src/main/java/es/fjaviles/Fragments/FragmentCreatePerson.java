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
import android.widget.Button;

import es.fjaviles.ApiRest.ApiAdapter;
import es.fjaviles.ApiRest.Model.Person;
import es.fjaviles.R;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;
import es.fjaviles.databinding.FragmentCreatePersonBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;


public class FragmentCreatePerson extends Fragment {

    private DialogLoading dialogLoading;
    private ViewModelMainPage VMMainPage;
    private FragmentCreatePersonBinding binding;


    public FragmentCreatePerson() {
        // Required empty public constructor
    }

    public static FragmentCreatePerson newInstance(String param1, String param2) {
        FragmentCreatePerson fragment = new FragmentCreatePerson();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreatePersonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VMMainPage = new ViewModelProvider(requireActivity()).get(ViewModelMainPage.class);
        binding.btnSavePerson.setOnClickListener(btn -> {dialogLoading = new DialogLoading(getActivity());addPerson(); dialogLoading.startLoadingDialog();});
    }


    private void addPerson(){
        Person newPerson = new Person(
                0,
                binding.edtTextName.getText().toString(),
                binding.edtTextSurName.getText().toString(),
                "",
                "",
                binding.edtTextAddress.getText().toString(),
                binding.edtTextPhone.getText().toString(),
                2
        );

        Call<Integer> callFillPersons = ApiAdapter.getApiService().addPerson(newPerson);
        callFillPersons.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                dialogLoading.stopLoadingDialog();
                if (response.code() == 204){
                    //TODO Hay que actualizar el ID con el ID de la bbdd
                    //TODO Hay dos maneras,
                    //TODO      1. Refrescar la lista al volver navegar hacia la pagina de la lista para que siempre este actualizada
                    //TODO      2. La peticion devuelve el ID de la persona creada
                    VMMainPage.addPerson(newPerson);
                    MotionToast.Companion.darkColorToast(requireActivity(),"Person added!","Person added successfully!",
                            MotionToast.TOAST_SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular));
                    VMMainPage.changeFragmentSelected("FragmentListPersons");
                }else{
                    onFailure(call,new Throwable("Parse error"));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                dialogLoading.stopLoadingDialog();

                //InfoUsers.showMessage(TypeMessage.TOAST_ERROR,requireActivity(),requireContext());

                MotionToast.Companion.darkColorToast(requireActivity(),"Error!","It has been realized correctly",
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