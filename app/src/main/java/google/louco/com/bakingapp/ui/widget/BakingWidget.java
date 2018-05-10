package google.louco.com.bakingapp.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.components.ServiceWidgetList;
import google.louco.com.bakingapp.components.WidgetService;
import google.louco.com.bakingapp.mvp.model.ActionActivity;
import google.louco.com.bakingapp.ui.activity.BakingActivity;
import google.louco.com.bakingapp.ui.activity.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {

    public static String action_key = "android.appwidget.action.APPWIDGET_UPDATE";

    private static String KEY_RECIPES = "recipes";

    private static String Ingridient = "";

    public final static String ITEM_POSITION = "item_position";
    public static String KEY_INGRIDIENTS = "Ingridient";

    public static List<Recipes> recipesList = new ArrayList();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Intent intenrRecipes) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);

        if(intenrRecipes == null) {
            views.setTextViewText(R.id.appwidget_text, "");
        }else{
            String ingridient = intenrRecipes.getStringExtra(KEY_RECIPES);
            views.setTextViewText(R.id.appwidget_text, ingridient);
        }

        Log.d("Louco", " updateAppWidget");
        Intent active = new Intent(context, ServiceWidgetList.class);
        views.setRemoteAdapter(R.id.lv_baking, active);

        Intent intent = new Intent(context , BakingWidget.class);
        intent.setAction("Position");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        views.setPendingIntentTemplate(R.id.lv_baking, pendingIntent);

        /**
         * active broadcast
         */
        /*Intent active = new Intent(context, BakingWidget.class);
        active.setAction(action_key);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,active,0);*/
        /**
         * active go Activity
         */

        /*Intent active = new Intent(context, BakingActivity.class);
        active.putExtra(Recipes.KEY_SERIALIZABLE, new Gson().toJson(recipesList.get(0)));

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,active,0);*/

       //views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingWidget.class));

        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_baking);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String text = intent.getAction();
        final long number = intent.getLongExtra(ITEM_POSITION, -1);

        if(number!= -1){
            Bundle bundle = new Bundle();
            bundle.putString(KEY_RECIPES,intent.getStringExtra(KEY_INGRIDIENTS));
            Intent intentIngredients = new Intent();
            intentIngredients.putExtras(bundle);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingWidget.class));
            for(int appWdgepid : appWidgetIds){
                updateAppWidget(context, appWidgetManager, appWdgepid, intentIngredients);
            }
        }

        if(text.equals(action_key)){
            Log.d("Louco", action_key+"  "+recipesList.size());
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingWidget.class));
            for(int appWdgepid : appWidgetIds){
                updateAppWidget(context, appWidgetManager, appWdgepid ,null);
            }
        }

        super.onReceive(context, intent);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            WidgetService.startWidgetService(context);
            updateAppWidget(context, appWidgetManager, appWidgetId, null);
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

