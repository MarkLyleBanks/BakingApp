package com.marklylebanks.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.ui.adapters.AdapterIngredient;
import com.marklylebanks.bakingapp.ui.adapters.AdapterStep;

public class FragmentRecipeSelected extends Fragment implements AdapterStep.onStepClickedListener {

    private int mRecipeIndex;

    FragmentRecipeSelected() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_selected, container, false);

        //set the recipe name
        TextView RecipeName = rootView.findViewById(R.id.tv_recipe_selected_name);
        RecipeName.setText(MainActivity.recipeList.get(mRecipeIndex).getName());

        RecyclerView ingredientRecycler = rootView.findViewById(R.id.rv_ingredients);
        AdapterIngredient ingredients = new AdapterIngredient(mRecipeIndex);
        ingredientRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientRecycler.setAdapter(ingredients);

        RecyclerView stepRecycler = rootView.findViewById(R.id.rv_steps);
        AdapterStep steps = new AdapterStep(mRecipeIndex, this);
        stepRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        stepRecycler.setAdapter(steps);


        return rootView;
    }

    public void setIngredientIndex(int index) {
        mRecipeIndex = index;
    }

    @Override
    public void onStepClicked(int position) {
        Toast.makeText(getContext(), "step " + position + " clicked", Toast.LENGTH_SHORT).show();
    }
}
