package com.example.tutorial1mvvmrecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder> {
    private List<Marker> markerObservableArrayList;

    public MarkerAdapter(List<Marker> markers) {
        markerObservableArrayList = markers;
    }

    @NonNull
    @Override
    public MarkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row_compass, parent, false);
        return new MarkerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkerViewHolder holder, int position) {
        holder.bind(markerObservableArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return markerObservableArrayList.size();
    }

    public class MarkerViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MarkerViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(com.example.tutorial1mvvmrecyclerview.BR.marker, obj);
            binding.executePendingBindings();
        }
    }
}
