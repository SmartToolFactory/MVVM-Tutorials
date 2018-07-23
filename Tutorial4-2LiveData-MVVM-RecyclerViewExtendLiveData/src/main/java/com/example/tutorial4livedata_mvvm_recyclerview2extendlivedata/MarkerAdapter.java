package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.MarkerLiveData;

import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder> {
    private List<MarkerLiveData> markerList;

    public MarkerAdapter(List<MarkerLiveData> markers) {
        markerList = markers;
    }

    @NonNull
    @Override
    public MarkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row, parent, false);
        return new MarkerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkerViewHolder holder, int position) {
        holder.bind(markerList.get(position));
    }

    @Override
    public int getItemCount() {
        return markerList == null ? 0 : markerList.size();
    }

    public class MarkerViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MarkerViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
          binding.setVariable(BR.marker, obj);
            binding.executePendingBindings();

        }
    }


}
