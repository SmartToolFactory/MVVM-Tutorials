package com.test.foursquaremultiple.venuedetail;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.foursquaremultiple.BR;
import com.test.foursquaremultiple.R;
import com.test.foursquaremultiple.model.UserComment;

import java.util.List;

/**
 * RecyclerView Adapter with data binding to display user comments as a list in {@link VenueDetailDialogFragment}
 */
public class UserCommentAdapter extends RecyclerView.Adapter<UserCommentAdapter.VenueViewHolder> {

    private List<UserComment> mUserComments;


    public UserCommentAdapter(List<UserComment> userComments) {
        mUserComments = userComments;
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row_user_comment, parent, false);
        return new VenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {

        // TODO FIX: User comment is NOT displayed even though comment exists???
        holder.bind(mUserComments.get(position));

        System.out.println("UserCommentAdapter onBindViewHolder() comment: " + mUserComments.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mUserComments == null ? 0 : mUserComments.size();

    }

    public class VenueViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public VenueViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.venue, obj);
            // forces the bindings to run immediately instead of delaying them until the next frame
            binding.executePendingBindings();
        }
    }

    public void resetComments(@NonNull List<UserComment> userComments) {
        this.mUserComments = userComments;
        notifyDataSetChanged();
    }


}
