package com.marklylebanks.bakingapp.ui.fragments;

import android.content.Context;
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
import com.marklylebanks.bakingapp.data.Constants;
import com.marklylebanks.bakingapp.ui.MainActivity;
import com.marklylebanks.bakingapp.ui.adapters.AdapterIngredient;
import com.marklylebanks.bakingapp.ui.adapters.AdapterStep;

public class FragmentRecipeSelected extends Fragment implements AdapterStep.onStepClickedListener {

    private int mRecipeIndex;
    private OnStepSelectedListener mListener;

    public interface OnStepSelectedListener {
        void onStepSelected(int step);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnStepSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement OnStepSelectedListener");
        }
    }

    public FragmentRecipeSelected() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_selected, container, false);

        if (savedInstanceState != null) {
            mRecipeIndex = savedInstanceState.getInt(Constants.SELECTED_RECIPE);
        }
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
        mListener.onStepSelected(position);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(Constants.SELECTED_RECIPE, mRecipeIndex);
    }
}
