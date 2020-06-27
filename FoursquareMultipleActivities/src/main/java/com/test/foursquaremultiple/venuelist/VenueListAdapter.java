package com.test.foursquaremultiple.venuelist;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.foursquaremultiple.BR;
import com.test.foursquaremultiple.R;
import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.view.callback.OnVenueClickListener;

import java.util.List;

/**
 * RecyclerView Adapter with data binding to display venues as a list in {@link VenueListFragment}
 */
public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.VenueViewHolder> {

    private List<Venue> mVenueList;

    private OnVenueClickListener mListener;

    public VenueListAdapter(List<Venue> venueList, OnVenueClickListener onVenueClickListener) {
        mVenueList = venueList;
        mListener = onVenueClickListener;
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row_venue_detail, parent, false);
        return new VenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        holder.bind(mVenueList.get(position));
    }

    @Override
    public int getItemCount() {
        return mVenueList == null ? 0 : mVenueList.size();
    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public VenueViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onClick(mVenueList.get(getLayoutPosition()));
                }
            });

        }

        void bind(Object obj) {
            binding.setVariable(BR.venue, obj);
            // forces the bindings to run immediately instead of delaying them until the next frame
            binding.executePendingBindings();
        }
    }

    public void setVenueList(@NonNull List<Venue> venueList) {
        this.mVenueList = venueList;
        notifyDataSetChanged();
    }


}
