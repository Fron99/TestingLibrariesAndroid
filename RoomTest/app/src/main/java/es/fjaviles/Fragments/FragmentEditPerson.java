package es.fjaviles.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.fjaviles.Dao.AppDatabase;
import es.fjaviles.Dao.Model.Person;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;
import es.fjaviles.databinding.FragmentEditPersonBinding;

public class FragmentEditPerson extends Fragment {

    private DialogLoading dialogLoading;
    private ViewModelMainPage VMMainPage;
    private FragmentEditPersonBinding binding;
    private Person personSelected;

    public FragmentEditPerson() {

    }

    public static FragmentEditPerson newInstance() {
        FragmentEditPerson fragment = new FragmentEditPerson();
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
        binding = FragmentEditPersonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity app = (AppCompatActivity) getActivity();
        app.getSupportActionBar().hide();

        VMMainPage = new ViewModelProvider(requireActivity()).get(ViewModelMainPage.class);
        dialogLoading = new DialogLoading(requireActivity());
        personSelected = VMMainPage.getPersonSelected();
        binding.edtTextName.setText(personSelected.getName());
        binding.edtTextSurName.setText(personSelected.getSurname());
        binding.edtTextAddress.setText(personSelected.getAddress());
        binding.edtTextPhone.setText(personSelected.getTelephone());
        //binding.edtDate.setText(VMMainPage.getPersonSelected().getFechaNacimiento().split("T")[0]);

        binding.edtDate.setOnClickListener(view1 -> { requireDate(); });

        binding.btnSavePerson.setOnClickListener(btn -> {
            dialogLoading.startLoadingDialog();
            if (personSelected.getId() == -1){
                savePerson();
            }else{
                editPerson();
            }
        });
    }


    private void editPerson(){

        Person personEdited = new Person(
                personSelected.getId(),
                binding.edtTextName.getText().toString(),
                binding.edtTextSurName.getText().toString(),
                "",//binding.edtDate.getText().toString(),
                "",
                binding.edtTextAddress.getText().toString(),
                binding.edtTextPhone.getText().toString()
        );

        AppDatabase.getDatabase(requireContext()).personDao().updatePersons(personEdited);
        dialogLoading.stopLoadingDialog();
        VMMainPage.changeFragmentSelected("FragmentListPersons");

    }

    private void savePerson(){

        Person personEdited = new Person(
                0,
                binding.edtTextName.getText().toString(),
                binding.edtTextSurName.getText().toString(),
                "",//binding.edtDate.getText().toString(),
                "",
                binding.edtTextAddress.getText().toString(),
                binding.edtTextPhone.getText().toString()
        );

        AppDatabase.getDatabase(requireContext()).personDao().insertPersons(personEdited);
        dialogLoading.stopLoadingDialog();
        VMMainPage.changeFragmentSelected("FragmentListPersons");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void requireDate(){
        String[] date = binding.edtDate.getText().toString().split("-");
        int yearSelected = Integer.parseInt(date[0]);
        int monthSelected = Integer.parseInt(date[1]);
        int daySelected = Integer.parseInt(date[2]);

        DatePickerDialog datePikerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> binding.edtDate.setText(year+"-"+month+"-"+dayOfMonth),
                yearSelected, monthSelected, daySelected);
        datePikerDialog.show();

    }
}