package google.louco.com.bakingapp.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.mvp.presenter.MainPresenterActivity;

public class MainActivity extends MvpAppCompatActivity {

    @InjectPresenter
    MainPresenterActivity presenterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




}
