package es.fjaviles.Fragments;

import android.app.DatePickerDialog;
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
import es.fjaviles.databinding.FragmentEditPersonBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEditPerson#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEditPerson extends Fragment {

    private DialogLoading dialogLoading;
    private ViewModelMainPage VMMainPage;
    private FragmentEditPersonBinding binding;

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

        VMMainPage = new ViewModelProvider(requireActivity()).get(ViewModelMainPage.class);
        binding.edtTextName.setText(VMMainPage.getPersonSelected().getNombre());
        binding.edtTextSurName.setText(VMMainPage.getPersonSelected().getApellidos());
        binding.edtTextAddress.setText(VMMainPage.getPersonSelected().getDireccion());
        binding.edtTextPhone.setText(VMMainPage.getPersonSelected().getTelefono());
        binding.edtDate.setText(VMMainPage.getPersonSelected().getFechaNacimiento().split("T")[0]);

        binding.edtDate.setOnClickListener(view1 -> { requireDate(); });

        binding.btnSavePerson.setOnClickListener(btn -> {dialogLoading = new DialogLoading(getActivity());editPerson(); dialogLoading.startLoadingDialog();});
    }


    private void editPerson(){

        Person personEdited = new Person(
                0,
                binding.edtTextName.getText().toString(),
                binding.edtTextSurName.getText().toString(),
                binding.edtDate.getText().toString(),
                "",
                binding.edtTextAddress.getText().toString(),
                binding.edtTextPhone.getText().toString(),
                2
        );

        if (VMMainPage.getPersonSelected().getId() != -1){

            Call<Integer> callFillPersons = ApiAdapter.getApiService().modifyPerson(VMMainPage.getPersonSelected().getId(),personEdited);
            callFillPersons.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    dialogLoading.stopLoadingDialog();
                    if (response.code() == 204){

                        InfoUsers.showMessageDarkColorToast(requireActivity(), requireContext(),
                                InfoUsers.TOAST_SUCCESS,
                                "Person modified!","Person modified correctly!");

                        VMMainPage.changeFragmentSelected("FragmentListPersons");
                    }else{
                        onFailure(call,new Throwable("Parse error"));
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                    dialogLoading.stopLoadingDialog();

                    InfoUsers.showMessageDarkColorToast(requireActivity(), requireContext(),
                            InfoUsers.TOAST_ERROR,
                            "Error!","The person could not be modified");
                }
            });

        }else{

            Call<Integer> callFillPersons = ApiAdapter.getApiService().addPerson(personEdited);
            callFillPersons.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    dialogLoading.stopLoadingDialog();
                    if (response.code() == 204){

                        InfoUsers.showMessageDarkColorToast(requireActivity(), requireContext(),
                                InfoUsers.TOAST_SUCCESS,
                                "Person added!","Person added correctly!");

                        VMMainPage.changeFragmentSelected("FragmentListPersons");
                    }else{
                        onFailure(call,new Throwable("Parse error"));
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                    dialogLoading.stopLoadingDialog();

                    InfoUsers.showMessageDarkColorToast(requireActivity(), requireContext(),
                            InfoUsers.TOAST_ERROR,
                            "Error!","The person could not be added");

                }
            });
        }


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