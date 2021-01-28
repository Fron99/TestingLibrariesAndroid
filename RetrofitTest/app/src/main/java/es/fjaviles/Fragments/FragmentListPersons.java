package es.fjaviles.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.fjaviles.Adapters.CustomAdapter;
import es.fjaviles.ApiRest.ApiAdapter;
import es.fjaviles.ApiRest.Model.Person;
import es.fjaviles.R;
import es.fjaviles.Utils.DialogLoading;
import es.fjaviles.ViewModels.ViewModelMainPage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.sanju.motiontoast.MotionToast;

public class FragmentListPersons extends Fragment {

    private ViewModelMainPage VMMainPage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton btnAddPerson;
    private DialogLoading dialogLoading;

    public FragmentListPersons() {

    }

    public static FragmentListPersons newInstance() {
        FragmentListPersons fragment = new FragmentListPersons();
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
        return inflater.inflate(R.layout.fragment_list_persons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VMMainPage = new ViewModelProvider(requireActivity()).get(ViewModelMainPage.class);
        dialogLoading = new DialogLoading(requireActivity());
        dialogLoading.startLoadingDialog();
        refreshPersons();
        CustomAdapter adapter = new CustomAdapter(VMMainPage.getPersons());
        RecyclerView list = view.findViewById(R.id.recycledView);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        btnAddPerson = view.findViewById(R.id.fab);

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VMMainPage.changeFragmentSelected("FragmentCreatePerson");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPersons();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                VMMainPage.setPersonSelected(VMMainPage.getPersons().get(position));
                VMMainPage.changeFragmentSelected("FragmentDetailsPersons");
            }
        });

    }

    private void refreshPersons(){
        Call<ArrayList<Person>> callFillPersons = ApiAdapter.getApiService().getPersons();
        callFillPersons.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                dialogLoading.stopLoadingDialog();
                if(response.isSuccessful()){
                    VMMainPage.addPersons(response.body());
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    onFailure(call,new Throwable("Parse error"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {

                MotionToast.Companion.darkColorToast(getActivity(),"ERROR","The person not deleted",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(getContext(),R.font.helvetica_regular));

            }
        });
    }


}