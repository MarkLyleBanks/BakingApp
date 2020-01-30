package com.marklylebanks.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.FakeData;
import com.marklylebanks.bakingapp.ui.adapters.AdapterRecipe;

public class FragmentRecipeMain extends Fragment  implements AdapterRecipe.AdapterRecipeOnClickHandler {
    public static final String SELECTED_RECIPE = "selected recipe";

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



        return rootView;
    }

    @Override
    public void onRecipeClicked(int position) {
        Intent intent = new Intent(getContext(), RecipeSelectedActivity.class);
        intent.putExtra(SELECTED_RECIPE, position);
        startActivity(intent);
    }
}
