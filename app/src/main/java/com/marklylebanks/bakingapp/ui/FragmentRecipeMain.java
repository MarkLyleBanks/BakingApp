package com.marklylebanks.bakingapp.ui;

import android.content.Context;
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

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.FakeData;
import com.marklylebanks.bakingapp.ui.adapters.AdapterRecipe;

public class FragmentRecipeMain extends Fragment  implements AdapterRecipe.AdapterRecipeOnClickHandler {

    OnRecipeClickedPassThrough mPassThrough;

    public interface OnRecipeClickedPassThrough {
        void clickPassThrough(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mPassThrough = (OnRecipeClickedPassThrough) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + " must implement OnRecipeClickedListener");
        }
    }

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
        mPassThrough.clickPassThrough(position);
        //Toast.makeText(getContext(),"item " + position + " clicked", Toast.LENGTH_LONG).show();
        Log.i("recycler", "onRecipeClicked: " + position);
    }
}
