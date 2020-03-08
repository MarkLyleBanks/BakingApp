package com.marklylebanks.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.marklylebanks.bakingapp.data.Constants;
import com.marklylebanks.bakingapp.ui.MainActivity;
import com.marklylebanks.bakingapp.ui.RecipeSelectedActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    private static Intent intent;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        String widgetText = context.getResources().getString(R.string.appwidget_text);//"test"; //context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);


        views.setTextViewText(R.id.appwidget_title, widgetText);
        if(MainActivity.recipeList.size() > 0) {
            intent = new Intent(context, RecipeSelectedActivity.class);
            intent.putExtra(Constants.SELECTED_RECIPE, MainActivity.recipeIndex);
        }else{
            intent = new Intent(context, MainActivity.class);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,0);
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
}

