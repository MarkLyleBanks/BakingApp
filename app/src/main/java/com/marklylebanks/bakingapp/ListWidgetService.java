package com.marklylebanks.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.marklylebanks.bakingapp.model.Ingredient;
import com.marklylebanks.bakingapp.ui.MainActivity;

import java.util.List;
import java.util.Locale;

public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}

class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    List<Ingredient> mIngredients;

    public ListRemoteViewsFactory(Context applicationContext){
        mContext = applicationContext;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mIngredients = MainActivity.recipeList
                .get(MainActivity.recipeIndex)
                .getIngredientsList();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredients == null){
            return 0;
        }else{
            return mIngredients.size();
        }
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (mIngredients == null || mIngredients.size() == 0) return null;

        RemoteViews views = new RemoteViews(mContext.getPackageName(),R.layout.widget_layout_ingredients);

        views.setTextViewText(R.id.widget_tv_ingredient_quantity, String.format(Locale.getDefault(),
                "%.1f",mIngredients.get(position).getQuantity()));
        views.setTextViewText(R.id.widget_tv_ingredient_measure, mIngredients.get(position).getMeasure());
        views.setTextViewText(R.id.widget_tv_ingredient_ingredient, mIngredients.get(position).getIngredient());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
