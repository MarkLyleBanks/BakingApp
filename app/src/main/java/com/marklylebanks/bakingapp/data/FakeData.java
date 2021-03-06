package com.marklylebanks.bakingapp.data;

import android.util.Log;

import com.marklylebanks.bakingapp.model.Ingredient;
import com.marklylebanks.bakingapp.model.Recipe;
import com.marklylebanks.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

     public static List<Recipe> getFakeData() {
        // create first recipe data
        List<Ingredient> ingredientsList1 = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient(2, "Cup", "Graham Cracker crumbs");
        ingredientsList1.add(ingredient1);
        Ingredient ingredient2 = new Ingredient(6, "TBLSP", "unsalted butter, melted");
        ingredientsList1.add(ingredient2);

        List<Step> stepsList1 = new ArrayList<>();
        Step steps1 = new Step(0, "Recipe Introduction", "Nutella Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4","");
        stepsList1.add(steps1);
        Step steps2 = new Step(1, "Starting Prep", "1. Preheat the oven to 350°F. Butter a 9\" deep dish pie pan.",
                "","");
        stepsList1.add(steps2);


        // create second recipe data
        List<Ingredient> ingredientsList2 = new ArrayList<>();
        Ingredient ingredients3 = new Ingredient(350, "G", "bittersweet chocolate");
        ingredientsList2.add(ingredients3);
        Ingredient ingredients4= new Ingredient(226, "G", "unsalted butter");
        ingredientsList2.add(ingredients4);

        List<Step> stepsList2 = new ArrayList<>();
        Step steps3 = new Step(0,"Recipe Introduction", "Brownie Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4", "");
        stepsList2.add(steps3);
        Step steps4 = new Step(1, "Starting prep", "1. Preheat the oven to 350�F. Butter the bottom and sides of a 9\"x13\" pan.",
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
