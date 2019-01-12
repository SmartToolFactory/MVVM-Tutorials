package com.test.foursquaresingle.view.venuelist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.foursquaresingle.MainActivity;
import com.test.foursquaresingle.R;
import com.test.foursquaresingle.databinding.FragmentVenueListBinding;
import com.test.foursquaresingle.model.Venue;
import com.test.foursquaresingle.view.callback.OnVenueClickListener;
import com.test.foursquaresingle.viewmodel.VenueDetailViewModel;
import com.test.foursquaresingle.viewmodel.VenueSearchViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class VenueListFragment extends DaggerFragment implements OnVenueClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    private FragmentVenueListBinding fragmentBinding;

    private VenueSearchViewModel mVenueListViewModel;

    private VenueDetailViewModel mVenueDetailViewModel;


    public static VenueListFragment newInstance() {
        VenueListFragment fragment = new VenueListFragment();

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

        mVenueListViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(VenueSearchViewModel.class);

        // Needed after rotation changes and setting back button navigation
        ((MainActivity) getActivity()).enableToolbarBackArrow(true);

        fragmentBinding.setViewModel(mVenueListViewModel);


        RecyclerView recyclerView = fragmentBinding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(itemDecoration);

        // Create adapter and bind data of ViewModel
        VenueListAdapter adapter = new VenueListAdapter(mVenueListViewModel.getVenueListResource().getValue().data, this);

        // Bind data to RecyclerView from adapter
        recyclerView.setAdapter(adapter);

        // List Resource is a wrapper class that contains web request status and response data of venues if successful
        mVenueListViewModel.getVenueListResource().observe(this, listResource -> {

            if (listResource == null) return;

            switch (listResource.status) {
                case SUCCESS:
                    adapter.setVenueList(listResource.data);
                    break;
            }
        });
    }

    @Override
    public void onClick(Venue venue) {
        mVenueDetailViewModel.queryVenueDetail(venue.getId());
    }

}

