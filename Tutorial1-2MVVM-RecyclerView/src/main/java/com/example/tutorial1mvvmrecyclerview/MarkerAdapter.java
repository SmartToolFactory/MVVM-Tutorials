package com.example.tutorial1mvvmrecyclerview;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tutorial1mvvmrecyclerview.model.Marker;

import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder> {
    private List<Marker> markerList;

    public MarkerAdapter(List<Marker> markers) {
        markerList = markers;
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
        holder.bind(markerList.get(position));
    }

    @Override
    public int getItemCount() {
        return markerList.size();
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
