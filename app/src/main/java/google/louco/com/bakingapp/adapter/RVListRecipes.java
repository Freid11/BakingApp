package google.louco.com.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;

public class RVListRecipes extends RecyclerView.Adapter<RVListRecipes.ViewHolder> {

    private List<Recipes> recipes = new ArrayList<>();
    private OnClickRecipe onClickRecipe;

    public RVListRecipes(OnClickRecipe onClickRecipe) {
        this.onClickRecipe = onClickRecipe;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recip_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void updateList(List<Recipes> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_recipe)
        TextView textView;

        @BindView(R.id.iv_image_baking)
        ImageView imageView;

        private Recipes lastRecipe;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> onClickRecipe.onClick(lastRecipe));
        }

        void bind(Recipes recipes){
            lastRecipe = recipes;
            textView.setText(recipes.getName());
            if(recipes.getImage().isEmpty()){
                imageView.setImageResource(R.drawable.ic_local_cafe_black_24dp);
            }else {
                Picasso.get()
                        .load(recipes.getImage())
                        .placeholder(R.drawable.ic_local_cafe_black_24dp)
                        .into(imageView);
            }
        }
    }

    public interface OnClickRecipe{
        void onClick(Recipes recipes);
    }
}
