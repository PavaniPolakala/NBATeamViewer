package com.example.nbateamviewer.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.FragmentTeamDetailsBinding;
import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;
import com.example.nbateamviewer.view.adapter.PlayerListAdapter;
import com.example.nbateamviewer.viewmodel.PlayerListViewModel;
import com.google.gson.Gson;

public class PlayerListFragment extends Fragment {
    public static final String TAG = "PlayerListFragment";
    private PlayerListAdapter playerListAdapter;
    private FragmentTeamDetailsBinding binding;
    private static final String KEY_TEAM_DETAILS = "team_details";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_details, container, false);

        playerListAdapter = new PlayerListAdapter();
        binding.playersList.setAdapter(playerListAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TeamModel team =  new Gson().fromJson(getArguments().getString(KEY_TEAM_DETAILS), TeamModel.class);
        PlayerListViewModel.Factory factory = new PlayerListViewModel.Factory(
                getActivity().getApplication(), team);
        final PlayerListViewModel viewModel =
                ViewModelProviders.of(this,factory).get(PlayerListViewModel.class);
            binding.setTeamDetailViewModel(viewModel);
        if (team.getPlayersList() != null) {
            playerListAdapter.setPlayerList(team.getPlayersList());
        }
        ((MainActivity)getActivity()).invalidateOptionsMenu();
    }

    public static PlayerListFragment getPlayerListFragment(TeamModel teamModel) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle args = new Bundle();

        args.putString(KEY_TEAM_DETAILS, new Gson().toJson(teamModel));
        fragment.setArguments(args);

        return fragment;
    }
}
