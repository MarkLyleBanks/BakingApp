package com.marklylebanks.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.data.Constants;
import com.marklylebanks.bakingapp.ui.fragments.FragmentSelectedStep;

public class StepSelectedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_selected);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        int recipe = intent.getIntExtra(Constants.SELECTED_RECIPE, 0);
        int step = intent.getIntExtra(Constants.SELECTED_STEP, 0);

        FragmentSelectedStep selectedStep = new FragmentSelectedStep();
        selectedStep.setRecipeId(recipe);
        selectedStep.setStepId(step);


        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.step_selected_container,selectedStep)
                .commit();
    }
}
