package com.test.foursquaremultiple.venuelist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.foursquaremultiple.R;
import com.test.foursquaremultiple.databinding.FragmentVenueListBinding;
import com.test.foursquaremultiple.model.Venue;
import com.test.foursquaremultiple.venuesearch.VenueSearchActivity;
import com.test.foursquaremultiple.view.callback.OnVenueClickListener;
import com.test.foursquaremultiple.viewmodel.VenueDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class VenueListFragment extends DaggerFragment implements OnVenueClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    private FragmentVenueListBinding fragmentBinding;


    private VenueDetailViewModel mVenueDetailViewModel;


    public static VenueListFragment newInstance(List<Venue> venueList) {
        VenueListFragment fragment = new VenueListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(VenueSearchActivity.VENUE_SEARCH_ARGS,
                (ArrayList<? extends Parcelable>) venueList);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_venue_list, container, false);

        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mVenueDetailViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(VenueDetailViewModel.class);

        List<Venue> venues = getArguments().getParcelableArrayList(VenueSearchActivity.VENUE_SEARCH_ARGS);

        if (venues != null && venues.size() > 0) {
            RecyclerView recyclerView = fragmentBinding.recyclerView;
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);

            recyclerView.addItemDecoration(itemDecoration);

            // Create adapter and bind data of ViewModel
            VenueListAdapter adapter = new VenueListAdapter(venues, this);

            // Bind data to RecyclerView from adapter
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(Venue venue) {
        mVenueDetailViewModel.queryVenueDetail(venue.getId());
    }

}

