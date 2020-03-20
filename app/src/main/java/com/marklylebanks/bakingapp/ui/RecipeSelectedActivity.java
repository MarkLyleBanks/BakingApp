package com.marklylebanks.bakingapp.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;

import com.marklylebanks.bakingapp.IngredientsWidgetProvider;
import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.Constants;
import com.marklylebanks.bakingapp.ui.fragments.FragmentRecipeSelected;
import com.marklylebanks.bakingapp.ui.fragments.FragmentSelectedStep;

public class RecipeSelectedActivity extends AppCompatActivity
        implements FragmentRecipeSelected.OnStepSelectedListener {

    int mPosition;
    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selected);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Intent intent = getIntent();
        mPosition = MainActivity.recipeIndex;


        FragmentRecipeSelected recipeSelected = new FragmentRecipeSelected();
        recipeSelected.setIngredientIndex(mPosition);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_selected_container, recipeSelected)
                    .commit();

            if (findViewById(R.id.recipe_two_pane) != null) {
                mTwoPane = true;
                FragmentSelectedStep selectedStep = new FragmentSelectedStep();
                selectedStep.setStepId(0);
                selectedStep.setRecipeId(mPosition);
                fragmentManager.beginTransaction()
                        .add(R.id.step_selected_container, selectedStep)
                        .commit();
            }

            updateWidgets();
        }
    }

    private void updateWidgets() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            MainActivity.recipeIndex = -1;
            updateWidgets();
            IngredientsWidgetProvider.updateIngredientsWidget(this);
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepSelected(int step) {
        if (mTwoPane) {
            FragmentSelectedStep selectedStep = new FragmentSelectedStep();
            selectedStep.setStepId(step);
            selectedStep.setRecipeId(mPosition);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.step_selected_container, selectedStep)
                    .commit();
        } else {
            Intent intent = new Intent(this, StepSelectedActivity.class);
            intent.putExtra(Constants.SELECTED_RECIPE, mPosition)
                    .putExtra(Constants.SELECTED_STEP, step);
            startActivity(intent);
        }
    }
}//Class end
