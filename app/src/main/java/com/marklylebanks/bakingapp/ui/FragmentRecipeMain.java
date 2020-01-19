package com.marklylebanks.bakingapp.ui;

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
import com.marklylebanks.bakingapp.ui.adapters.AdapterRecipe;

public class FragmentRecipeMain extends Fragment {

    public FragmentRecipeMain(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_master, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_main);
        AdapterRecipe adapterRecipe = new AdapterRecipe();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterRecipe);

        return rootView;
    }
}
