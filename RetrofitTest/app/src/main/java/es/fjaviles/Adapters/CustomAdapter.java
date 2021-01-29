package es.fjaviles.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        private final TextView textView;

        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textNameAndSurname);

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

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter(ArrayList<Person> dataSet) {
        persons = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_list_persons, viewGroup, false);

        return new ViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(persons.get(position).getNombre()+" "+ persons.get(position).getApellidos());
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
