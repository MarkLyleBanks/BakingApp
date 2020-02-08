package com.marklylebanks.bakingapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.ui.MainActivity;

public class FragmentSelectedStep extends Fragment {

    int recipeId;
    int stepId;

    public FragmentSelectedStep() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selected_step, container, false);

        TextView shortDesc = rootView.findViewById(R.id.tv_selected_step_short_description);
        TextView description = rootView.findViewById(R.id.tv_selected_step_description);

        shortDesc.setText(MainActivity.recipeList.get(recipeId).getStepsList().get(stepId).getShortDescription());
        description.setText(MainActivity.recipeList.get(recipeId).getStepsList().get(stepId).getDescription());

        return rootView;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }
}
