package com.marklylebanks.bakingapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.model.Step;
import com.marklylebanks.bakingapp.ui.MainActivity;

import java.util.List;

public class AdapterStep extends RecyclerView.Adapter<AdapterStep.ViewHolder> {

    public interface onStepClickedListener {
        public void onStepClicked(int position);
    }

    private List<Step> mStepList;
    onStepClickedListener mClickedListener;

    public AdapterStep(int index, onStepClickedListener listener) {
        mStepList = MainActivity.recipeList.get(index).getStepsList();
        mClickedListener = listener;

    }

    @NonNull
    @Override
    public AdapterStep.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_steps, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step currentStep = mStepList.get(position);
        holder.mShortDescription.setText(currentStep.getShortDescription());
    }


    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mShortDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mShortDescription = itemView.findViewById(R.id.tv_layout_step);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickedListener.onStepClicked(getAdapterPosition());
        }
    }
}
