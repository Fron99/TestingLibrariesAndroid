package es.fjaviles.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import es.fjaviles.ApiRest.Model.Person;
import es.fjaviles.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Person> persons;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        public void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtViewNameAndSurname, txtViewTelephone, txtViewBirthdate;
        private final ImageView imgViewPerson;

        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);
            txtViewNameAndSurname = view.findViewById(R.id.txtViewNameAndSurname);
            txtViewTelephone = view.findViewById(R.id.txtViewTelephone);
            txtViewBirthdate = view.findViewById(R.id.txtViewBirthdate);
            imgViewPerson = view.findViewById(R.id.imgViewPerson);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public TextView getTxtViewNameAndSurname() {
            return txtViewNameAndSurname;
        }
        public TextView getTxtViewTelephone() {
            return txtViewTelephone;
        }
        public TextView getTxtViewBirthdate() {
            return txtViewBirthdate;
        }
        public ImageView getImgViewPerson() {
            return imgViewPerson;
        }

        public void setImgViewPerson(int id){
            imgViewPerson.setImageResource(id);}
        public void setTxtViewNameAndSurname(String nameAndSurname){
            txtViewNameAndSurname.setText(nameAndSurname);
        }
        public void setTxtViewTelephone(String telephone){
            txtViewTelephone.setText(telephone);
        }
        public void setTxtViewBirthdate(String birthdate){
            txtViewBirthdate.setText(birthdate);
        }

    }

    public CustomAdapter(ArrayList<Person> dataSet) {
        persons = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_list_persons, viewGroup, false);

        return new ViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Random rnd = new Random();
        int id = R.drawable.shrek;
        switch (rnd.nextInt(11)){
            case 0: id = R.drawable.gato; break;
            case 1: id = R.drawable.lisa; break;
            case 2: id = R.drawable.persona1; break;
            case 3: id = R.drawable.persona2; break;
            case 4: id = R.drawable.persona3; break;
            case 5: id = R.drawable.persona4; break;
            case 6: id = R.drawable.persona5; break;
            case 7: id = R.drawable.persona6; break;
            case 8: id = R.drawable.persona7; break;
            case 9: id = R.drawable.persona8; break;
            case 10: id = R.drawable.shrek; break;
        }

        viewHolder.setImgViewPerson(id);
        viewHolder.setTxtViewNameAndSurname(persons.get(position).getNombre()+" "+ persons.get(position).getApellidos());
        viewHolder.setTxtViewTelephone(persons.get(position).getTelefono());
        viewHolder.setTxtViewBirthdate(persons.get(position).getFechaNacimiento().split("T")[0]);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void addPersons(ArrayList<Person> persons){
        this.persons.clear();
        this.persons.addAll((ArrayList<Person>)persons.clone());
    }

    public void addPerson(Person persons){
        this.persons.add(persons);
    }

}
