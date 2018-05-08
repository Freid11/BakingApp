package google.louco.com.bakingapp.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.R;

public class DescriptionFragment extends Fragment {

    private String textDescription;

    @BindView(R.id.tv_description_fragment)
    TextView description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(container !=null) {
            inflater = LayoutInflater.from(container.getContext());
            View view = inflater.inflate(R.layout.description_fragment, container, false);
            ButterKnife.bind(this, view);
            return view;
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        description.setText(textDescription);
    }

    public void setText(String text) {
        textDescription = text;
    }
}
