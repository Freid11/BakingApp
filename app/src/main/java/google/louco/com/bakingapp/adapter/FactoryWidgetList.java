package google.louco.com.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.ui.widget.BakingWidget;

public class FactoryWidgetList implements RemoteViewsFactory {

    private List<Recipes> recipesList;
    private Context context;

    public FactoryWidgetList(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        recipesList = BakingWidget.recipesList;
    }

    @Override
    public void onDataSetChanged() {
        onCreate();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return recipesList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item_list_view);
        remoteViews.setTextViewText(R.id.tv_item_widget, recipesList.get(position).getName());

        Bundle extras = new Bundle();
        extras.putLong(BakingWidget.ITEM_POSITION, position);
        extras.putString(BakingWidget.KEY_INGRIDIENTS, recipesList.get(position).getIngredients());
        Intent clickIntent = new Intent();
        clickIntent.putExtras(extras);

        remoteViews.setOnClickFillInIntent(R.id.tv_item_widget, clickIntent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
