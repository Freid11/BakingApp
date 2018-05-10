package google.louco.com.bakingapp.components;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.mvp.model.RetrofitServer;
import google.louco.com.bakingapp.mvp.presenter.MainPresenterActivity;
import google.louco.com.bakingapp.ui.activity.BakingActivity;
import google.louco.com.bakingapp.ui.widget.BakingWidget;
import io.reactivex.observers.DisposableObserver;

public class WidgetService extends IntentService {

    public static final String WIDGET_SERVICE = "WidgetService";
    public static final String ANDROID_APPWIDGET_ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";

    public WidgetService() {
        super(WIDGET_SERVICE);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new RetrofitServer().getRecipes(new RequestRecipes());
    }

    class RequestRecipes extends DisposableObserver<List<Recipes>> {

        @Override
        public void onNext(List<Recipes> recipes) {
            BakingWidget.recipesList = recipes;
            Intent intent = new Intent(ANDROID_APPWIDGET_ACTION_APPWIDGET_UPDATE);
            intent.setAction(BakingWidget.action_key);
            sendBroadcast(intent);
            Log.d("Louco", "  onReceive");
            this.onComplete();
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    public static void startWidgetService(Context context){
        Intent intent = new Intent(context, WidgetService.class);
        context.startService(intent);
    }
}
