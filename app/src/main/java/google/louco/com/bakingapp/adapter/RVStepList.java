package google.louco.com.bakingapp.adapter;

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
        holder.Bind(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void UpdateList(List<Step> steps){
        this.steps = steps;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Step LastStep;

        @BindView(R.id.tv_name_step)
        TextView nameStep;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> onClickStep.onClick(LastStep));
        }

        private void Bind(Step step){
            LastStep = step;
            String text = step.getShortDescription();
            nameStep.setText(text);
        }
    }

    public interface OnClickStepListener{
        void onClick(Step step);
    }

}
