package com.marklylebanks.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.marklylebanks.bakingapp.R;

public class RecipeSelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selected);
        Intent intent = getIntent();
        int position = intent.getIntExtra(MainActivity.SELECTED_RECIPE, 0);



        FragmentRecipeSelected recipeSelected = new FragmentRecipeSelected();
        recipeSelected.setIngredientIndex(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_selected_container, recipeSelected)
                .commit();
    }

}
