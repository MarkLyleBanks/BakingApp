package com.marklylebanks.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.marklylebanks.bakingapp.ui.MainActivity;
import com.marklylebanks.bakingapp.ui.RecipeSelectedActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    private static Intent intent;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String widgetTitle = context.getResources().getString(R.string.app_name);
        String widgetRecipe;

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);

        Intent listIntent = new Intent (context, ListWidgetService.class);
        views.setRemoteAdapter(R.id.appwidget_list, listIntent);
        views.setEmptyView(R.id.appwidget_list, R.id.appwidget_empty_view);

        if (MainActivity.recipeIndex >= 0) {
            widgetRecipe = MainActivity.recipeList.get(MainActivity.recipeIndex).getName();
            Log.i("widget", "updateAppWidget: recipeIndex " + MainActivity.recipeIndex);

            intent = new Intent(context, RecipeSelectedActivity.class);

        } else {
            widgetRecipe = "";
            intent = new Intent(context, MainActivity.class);
        }

        views.setTextViewText(R.id.appwidget_title, widgetTitle);
        views.setTextViewText(R.id.appwidget_recipe, widgetRecipe);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent);




        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void updateIngredientsWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetsIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, IngredientsWidgetProvider.class));
        for (int appWidgetsId : appWidgetsIds) {
            updateAppWidget(context, appWidgetManager, appWidgetsId);
        }
    }
}

