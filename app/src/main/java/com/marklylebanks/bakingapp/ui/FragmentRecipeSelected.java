package com.marklylebanks.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.ui.adapters.AdapterIngredient;

public class FragmentRecipeSelected extends Fragment {

    private int mRecipeIndex;

    FragmentRecipeSelected() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_selected, container, false);

        TextView RecipeName = rootView.findViewById(R.id.tv_recipe_selected_name);
        RecipeName.setText(MainActivity.recipeList.get(mRecipeIndex).getName());

        RecyclerView ingredientRecycler = rootView.findViewById(R.id.rv_ingredients);
        AdapterIngredient ingredients = new AdapterIngredient(mRecipeIndex);
        ingredientRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientRecycler.setAdapter(ingredients);


        return rootView;
    }

    public void setIngredientIndex(int index) {
        mRecipeIndex = index;
    }
}
