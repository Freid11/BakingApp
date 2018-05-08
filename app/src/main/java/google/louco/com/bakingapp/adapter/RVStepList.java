package google.louco.com.bakingapp.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.R;

public class RVStepList extends RecyclerView.Adapter<RVStepList.ViewHolder> {

    private List<Step> steps = new ArrayList<>();
    private OnClickStepListener onClickStep;
    private int row_index = -1;

    public RVStepList(OnClickStepListener onClickStep) {
        this.onClickStep = onClickStep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_step_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Bind(steps.get(position), position);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void UpdateList(List<Step> steps) {
        this.steps = steps;

        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Step LastStep;
        private int position_item;
        View view;

        @BindView(R.id.tv_name_step)
        TextView nameStep;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                onClickStep.onClick(LastStep);
                row_index = position_item;
                notifyDataSetChanged();
            });
        }

        private void Bind(Step step, int position_item) {
            this.position_item = position_item;
            LastStep = step;
            String text = step.getShortDescription();
            nameStep.setText(text);
            if(row_index == -1){
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            if(row_index == position_item){
                view.setBackgroundColor(Color.parseColor("#3F51B5"));
            }else{
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        }

    }

    public interface OnClickStepListener {
        void onClick(Step step);
    }

}
