package com.marklylebanks.bakingapp.data;

import android.util.Log;

import com.marklylebanks.bakingapp.model.Ingredients;
import com.marklylebanks.bakingapp.model.Recipe;
import com.marklylebanks.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

     public static List<Recipe> getFakeData() {
        // create first recipe data
        List<Ingredients> ingredientsList1 = new ArrayList<>();
        Ingredients ingredient1 = new Ingredients(2, "Cup", "Graham Cracker crumbs");
        ingredientsList1.add(ingredient1);
        Ingredients ingredient2 = new Ingredients(6, "TBLSP", "unsalted butter, melted");
        ingredientsList1.add(ingredient2);

        List<Steps> stepsList1 = new ArrayList<>();
        Steps steps1 = new Steps(0, "Recipe Introduction", "Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4","");
        stepsList1.add(steps1);
        Steps steps2 = new Steps(1, "Starting Prep", "1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.",
                "","");
        stepsList1.add(steps2);


        // create second recipe data
        List<Ingredients> ingredientsList2 = new ArrayList<>();
        Ingredients ingredients3 = new Ingredients(350, "G", "bittersweet chocolate");
        ingredientsList2.add(ingredients3);
        Ingredients ingredients4= new Ingredients(226, "G", "unsalted butter");
        ingredientsList2.add(ingredients4);

        List<Steps> stepsList2 = new ArrayList<>();
        Steps steps3 = new Steps(0,"Recipe Introduction", "Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4", "");
        stepsList2.add(steps3);
        Steps steps4 = new Steps(1, "Starting prep", "1. Preheat the oven to 350�F. Butter the bottom and sides of a 9\"x13\" pan.",
                "", "");
        stepsList2.add(steps4);

        Recipe recipe1 = new Recipe(1, "Nutella Pie", ingredientsList1, stepsList1, 8, "");
        Recipe recipe2 = new Recipe(2, "Brownies", ingredientsList2, stepsList2, 8, "");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        Recipe temp = recipes.get(1);
        Log.i("recycler", " getFakeData: " + recipes.size());
        Log.i("recycler", " temp name: " + recipe2.getName());
        return recipes;
    }
}
