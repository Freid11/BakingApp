package google.louco.com.bakingapp.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.R;

public class DescriptionFragment extends Fragment {

    private String textDescription;
    private String thumbnailURL;


    @BindView(R.id.tv_description_fragment)
    TextView description;

    @BindView(R.id.iv_description)
    ImageView imageView;

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
        if(textDescription!=null) {
            description.setText(textDescription);
        }

        if(thumbnailURL!=null) {
            if (thumbnailURL.isEmpty()) {
                imageView.setImageResource(R.drawable.ic_local_cafe_black_24dp);
            } else {
                Picasso.get()
                        .load(thumbnailURL)
                        .placeholder(R.drawable.ic_local_cafe_black_24dp)
                        .into(imageView);
            }
        }

    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public void setText(String text) {
        textDescription = text;
    }
}
