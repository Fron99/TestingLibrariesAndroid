package es.fjaviles.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import es.fjaviles.Dao.Model.Person;
import es.fjaviles.R;

public class CusAdapPersons extends RecyclerView.Adapter<CusAdapPersons.ViewHolder> implements Filterable {

    private ArrayList<Person> persons;
    private ArrayList<Person> personsFilter;
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

    public CusAdapPersons(ArrayList<Person> dataSet) {
        personsFilter = dataSet;
        persons = new ArrayList<>(dataSet);
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
        viewHolder.setTxtViewNameAndSurname(personsFilter.get(position).getName()+" "+ persons.get(position).getSurname());
        viewHolder.setTxtViewTelephone(personsFilter.get(position).getTelephone());
        //viewHolder.setTxtViewBirthdate(persons.get(position).getFechaNacimiento().split("T")[0]);
    }

    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    public void addPersons(ArrayList<Person> persons){
        this.persons.clear();
        this.persons.addAll((ArrayList<Person>)persons.clone());
    }

    public void addPerson(Person persons){
        this.persons.add(persons);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Person> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(persons);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Person item : persons) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            personsFilter.clear();
            personsFilter.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
