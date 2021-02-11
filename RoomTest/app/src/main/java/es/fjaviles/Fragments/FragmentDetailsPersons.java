package es.fjaviles.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.fjaviles.Dao.AppDatabase;
import es.fjaviles.Dao.Model.Person;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;
import es.fjaviles.databinding.FragmentDetailsPersonsBinding;



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

        binding.edtTextName.setText(VMMainPage.getPersonSelected().getName());
        binding.edtTextSurName.setText(VMMainPage.getPersonSelected().getSurname());
        binding.edtTextAddress.setText(VMMainPage.getPersonSelected().getAddress());
        binding.edtTextPhone.setText(VMMainPage.getPersonSelected().getTelephone());
        //binding.edtDate.setText(VMMainPage.getPersonSelected().getFechaNacimiento().split("T")[0]);

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
        AppDatabase.getDatabase(requireContext()).personDao().deletePersons(personSelected);
        VMMainPage.removePerson(personSelected);
        dialogLoading.stopLoadingDialog();
        VMMainPage.changeFragmentSelected("FragmentListPersons");
    }
}