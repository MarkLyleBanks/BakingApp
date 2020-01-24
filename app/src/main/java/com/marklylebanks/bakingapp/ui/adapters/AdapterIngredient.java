package com.marklylebanks.bakingapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.model.Ingredient;

import java.util.List;

public class AdapterIngredient extends RecyclerView.Adapter<AdapterIngredient.ViewHolder> {

    private List <Ingredient> mIngredientList;

    public AdapterIngredient(List<Ingredient> ingredients) {
        mIngredientList = ingredients;
    }

    @NonNull
    @Override
    public AdapterIngredient.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_ingredients, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIngredient.ViewHolder holder, int position) {
        Ingredient currentIngredient = mIngredientList.get(position);
        String quantity = String.format("%.1f", currentIngredient.getQuantity());
        holder.mQuantity.setText(quantity);
        holder.mMeasure.setText(currentIngredient.getMeasure());
        holder.mIngredient.setText(currentIngredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mQuantity;
        private TextView mMeasure;
        private TextView mIngredient;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuantity = itemView.findViewById(R.id.tv_ingredient_quantity);
            mMeasure = itemView.findViewById(R.id.tv_ingredient_measure);
            mIngredient = itemView.findViewById(R.id.tv_ingredient_ingredient);
        }
    }
}
