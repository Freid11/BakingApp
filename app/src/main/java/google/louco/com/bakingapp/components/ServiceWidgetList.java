package google.louco.com.bakingapp.components;

import android.content.Intent;
import android.widget.RemoteViewsService;

import google.louco.com.bakingapp.adapter.FactoryWidgetList;

public class ServiceWidgetList extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FactoryWidgetList(getApplicationContext());
    }
}
