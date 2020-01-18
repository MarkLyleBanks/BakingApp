package com.marklylebanks.bakingapp.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.FakeData;
import com.marklylebanks.bakingapp.model.Recipe;

import java.util.List;


public class AdapterRecipe extends RecyclerView.Adapter<AdapterRecipe.ViewHolder>{

    interface OnItemClicked {
        
    }

    private List<Recipe> recipeList;

    public AdapterRecipe(){
        recipeList = FakeData.getFakeData();
        Log.i("recycler", " AdapterRecipe: listSize: " + recipeList.size());
    }


    @NonNull
    @Override
    public AdapterRecipe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        Log.i("recycler", "AdapterRecipe onBindViewHolder: Name: " + currentRecipe.getName());
        Log.i("recycler", "AdapterRecipe onBindViewHolder: Servings: " + currentRecipe.getServings());
        Log.i("recycler", "AdapterRecipe onBindViewHolder: Image: " + currentRecipe.getImage());
        if (currentRecipe.getImage().equals("") || currentRecipe.getImage().equals(null)){
            holder.mRecipeImage.setVisibility(View.INVISIBLE);
            Log.i("recycler", "AdapterRecipe onBindViewHolder: Image is empty");
        }else{

        }

        holder.mRecipeName.setText(currentRecipe.getName());
        holder.mServings.setText(Integer.toString(currentRecipe.getServings()));
    }

    @Override
    public int getItemCount() {
        Log.i("recycler", "getItemCount(): " + recipeList.size());
        return recipeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public androidx.appcompat.widget.AppCompatImageView mRecipeImage;
        public TextView mRecipeName;
        public TextView mServings;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeImage = itemView.findViewById(R.id.iv_recipe_image);
            mRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            mServings = itemView.findViewById(R.id.tv_recipe_servings);
        }
    }
}
