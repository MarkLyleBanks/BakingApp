package com.marklylebanks.bakingapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.IngredientsWidgetProvider;
import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.FakeData;
import com.marklylebanks.bakingapp.ui.MainActivity;
import com.marklylebanks.bakingapp.ui.RecipeSelectedActivity;
import com.marklylebanks.bakingapp.ui.adapters.AdapterRecipe;

public class FragmentRecipeMain extends Fragment  implements AdapterRecipe.AdapterRecipeOnClickHandler {

    public FragmentRecipeMain(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_master, container, false);
        if (MainActivity.recipeList.size()==0 || MainActivity.recipeList == null){
            MainActivity.recipeList = FakeData.getFakeData();
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_main);
        AdapterRecipe adapterRecipe = new AdapterRecipe(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterRecipe);

        //IngredientsWidgetProvider.onUpdate;

        return rootView;
    }

    @Override
    public void onRecipeClicked(int position) {
        MainActivity.recipeIndex = position;
        Log.i("widget", "onRecipeClicked: position is: " + position);
        Log.i("widget", "onRecipeClicked: recipe is: " + MainActivity.recipeIndex);
        IngredientsWidgetProvider.updateIngredientsWidget(getContext());
        IngredientsWidgetProvider.updateIngredientsWidget(getContext());
        Intent intent = new Intent(getContext(), RecipeSelectedActivity.class);
        startActivity(intent);
    }
}
