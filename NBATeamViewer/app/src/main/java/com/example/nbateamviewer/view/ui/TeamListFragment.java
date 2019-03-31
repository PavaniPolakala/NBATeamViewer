package com.example.nbateamviewer.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.FragmentTeamlistBinding;
import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;
import com.example.nbateamviewer.view.adapter.TeamListAdapter;
import com.example.nbateamviewer.view.callback.TeamCallback;
import com.example.nbateamviewer.viewmodel.TeamListViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamListFragment extends Fragment {
    public static final String TAG = "TeamListFragment";
    private TeamListAdapter teamListAdapter;
    private FragmentTeamlistBinding binding;
    public TeamListViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teamlist, container, false);

        teamListAdapter = new TeamListAdapter(teamCallback);
        binding.teamsList.setAdapter(teamListAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TeamListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(TeamListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getTeamListObservable().observe(this, new Observer<List<TeamModel>>() {
            @Override
            public void onChanged(@Nullable List<TeamModel> teamsList) {
                if (teamsList != null) {
                    binding.setIsLoading(false);
                    Collections.sort(teamsList,nameComparator);
                    teamListAdapter.setTeamsList(teamsList);
                }
            }
        });
    }

    // When an item is clicked in listview, this callback is called.
    private final TeamCallback teamCallback = new TeamCallback() {
        @Override
        public void onClick(TeamModel teamModel) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(teamModel);
            }
        }
    };

    // List details are sorted alphabetically
    public void sortDataByName (){
        if (null == teamListAdapter || teamListAdapter.getTeamList() == null)
            return;

        Collections.sort(teamListAdapter.getTeamList(),nameComparator);
        teamListAdapter.notifyDataSetChanged();
    }

    // List details are sorted by wins
    public void sortDataByWins (){
        if (null == teamListAdapter || teamListAdapter.getTeamList() == null)
            return;

        Collections.sort(teamListAdapter.getTeamList(),winsComparator);
        teamListAdapter.notifyDataSetChanged();
    }

    // List details are sorted by losses
    public void sortDataByLosses (){
        if (null == teamListAdapter || teamListAdapter.getTeamList() == null)
            return;

        Collections.sort(teamListAdapter.getTeamList(),lossesComparator);
        teamListAdapter.notifyDataSetChanged();
    }

    public static Comparator<TeamModel> nameComparator = new Comparator<TeamModel>() {

        public int compare(TeamModel team1, TeamModel team2) {
            String name1 = team1.getFullName().toUpperCase();
            String name2 = team2.getFullName().toUpperCase();

            return name1.compareTo(name2);
        }};

    public static Comparator<TeamModel> winsComparator = new Comparator<TeamModel>() {

        public int compare(TeamModel team1, TeamModel team2) {
            return (team1.getWins()) - (team2.getWins());
        }};

    public static Comparator<TeamModel> lossesComparator = new Comparator<TeamModel>() {

        public int compare(TeamModel team1, TeamModel team2) {
            return (team1.getLosses()) - (team2.getLosses());
        }};


}

