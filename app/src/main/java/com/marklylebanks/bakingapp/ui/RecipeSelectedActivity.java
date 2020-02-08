package com.marklylebanks.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.Constants;
import com.marklylebanks.bakingapp.ui.fragments.FragmentRecipeSelected;

public class RecipeSelectedActivity extends AppCompatActivity
        implements FragmentRecipeSelected.OnStepSelectedListener {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selected);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        position = intent.getIntExtra(Constants.SELECTED_RECIPE, 0);



        FragmentRecipeSelected recipeSelected = new FragmentRecipeSelected();
        recipeSelected.setIngredientIndex(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_selected_container, recipeSelected)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepSelected(int step) {

        Intent intent = new Intent(this, StepSelectedActivity.class);
        intent.putExtra(Constants.SELECTED_RECIPE, position)
                .putExtra(Constants.SELECTED_STEP, step);
        startActivity(intent);
    }
}//Class end
