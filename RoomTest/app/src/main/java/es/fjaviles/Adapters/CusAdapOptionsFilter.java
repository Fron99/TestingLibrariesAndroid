package es.fjaviles.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import es.fjaviles.Dao.Model.Person;
import es.fjaviles.R;

public class CusAdapOptionsFilter extends RecyclerView.Adapter<CusAdapOptionsFilter.ViewHolder> {

    private ArrayList<String> optionsFilter;
    private CusAdapPersons.OnItemClickListener mListener;

    public interface OnItemClickListener{

        public void onItemClick(int position);

    }

    public void setOnItemClickListener(CusAdapPersons.OnItemClickListener listener){
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtViewOptionFilter;

        public ViewHolder(View view, CusAdapPersons.OnItemClickListener listener) {
            super(view);
            txtViewOptionFilter = view.findViewById(R.id.txtViewOptionFilter);
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

        public TextView getTxtViewOptionFilter() {
            return txtViewOptionFilter;
        }

        public void setTxtViewOptionFilter(String optionFilter){
            txtViewOptionFilter.setText(optionFilter);
        }


    }

    public CusAdapOptionsFilter(ArrayList<String> dataSet) {
        optionsFilter = dataSet;
    }

    @NonNull
    @Override
    public CusAdapOptionsFilter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_option_filter, viewGroup, false);

        return new CusAdapOptionsFilter.ViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(CusAdapOptionsFilter.ViewHolder viewHolder, final int position) {

        viewHolder.txtViewOptionFilter.setText(optionsFilter.get(position));

    }

    @Override
    public int getItemCount() {
        return optionsFilter.size();
    }

    public void addOptions(ArrayList<String> options){
        this.optionsFilter.clear();
        this.optionsFilter.addAll((ArrayList<String>)options.clone());
    }

    public void addOption(String option){
        this.optionsFilter.add(option);
    }

}
