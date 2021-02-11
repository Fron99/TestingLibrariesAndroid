package es.fjaviles.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.fjaviles.ApiRest.ApiAdapter;
import es.fjaviles.ApiRest.Model.Person;

import es.fjaviles.R;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.Utils.InfoUsers;
import es.fjaviles.ViewModels.ViewModelMainPage;
import es.fjaviles.databinding.FragmentDetailsPersonsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;


public class FragmentDetailsPersons extends Fragment {

    private ViewModelMainPage VMMainPage;
    private Person personSelected;
    private FragmentDetailsPersonsBinding binding;
    private DialogLoading dialogLoading;

    public FragmentDetailsPersons() {

    }

    public static FragmentDetailsPersons newInstance() {
        FragmentDetailsPersons fragment = new FragmentDetailsPersons();
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
        binding = FragmentDetailsPersonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VMMainPage = new ViewModelProvider(requireActivity()).get(ViewModelMainPage.class);
        personSelected = VMMainPage.getPersonSelected();

        binding.edtTextName.setText(VMMainPage.getPersonSelected().getNombre());
        binding.edtTextSurName.setText(VMMainPage.getPersonSelected().getApellidos());
        binding.edtTextAddress.setText(VMMainPage.getPersonSelected().getDireccion());
        binding.edtTextPhone.setText(VMMainPage.getPersonSelected().getTelefono());
        binding.edtDate.setText(VMMainPage.getPersonSelected().getFechaNacimiento().split("T")[0]);

        binding.btnDelete.setOnClickListener(v -> {
            dialogLoading = new DialogLoading(getActivity());
            dialogLoading.startLoadingDialog();
            deletePerson();
        });

        binding.btnEdit.setOnClickListener(v -> VMMainPage.changeFragmentSelected("FragmentEditPerson"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void deletePerson(){
        Call<Integer> callFillPersons = ApiAdapter.getApiService().removePerson(personSelected.getId());
        callFillPersons.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                dialogLoading.stopLoadingDialog();
                if (response.code() == 204){
                    VMMainPage.removePerson(personSelected);

                    /*InfoUsers.showMessageDarkColorToast(getActivity(), getContext(),
                            InfoUsers.TOAST_DELETE,
                            "Deleted!","Deleted Completed successfully!");*/

                    MotionToast.Companion.darkColorToast(getActivity(),
                            "Deleted!","Deleted Completed successfully!",
                            MotionToast.TOAST_DELETE,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(getContext(), R.font.helvetica_regular));


                    VMMainPage.changeFragmentSelected("FragmentListPersons");
                }else{
                    onFailure(call,new Throwable("Parse error"));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                dialogLoading.stopLoadingDialog();

                /*InfoUsers.showMessageDarkColorToast(getActivity(), getContext(),
                        InfoUsers.TOAST_DELETE,
                        "Error!","It has been realized correctly");*/

                MotionToast.Companion.darkColorToast(getActivity(),
                        "Error!","It wasn`t done correctly",
                        MotionToast.TOAST_DELETE,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(), R.font.helvetica_regular));

            }
        });
    }
}