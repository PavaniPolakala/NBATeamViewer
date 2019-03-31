package com.example.nbateamviewer.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamlistItemViewBinding;
import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;
import com.example.nbateamviewer.view.callback.TeamCallback;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    List<? extends TeamModel> teamsList;
    public static final String TAG = "TeamListAdapter";

    @Nullable
    private final TeamCallback teamListClickCallback;

    public TeamListAdapter(@Nullable TeamCallback projectClickCallback) {
        this.teamListClickCallback = projectClickCallback;
    }

    public void setTeamsList(final List<? extends TeamModel> teamsList) {
        this.teamsList = teamsList;
        Log.d(TAG,"set team list "+teamsList.size());
        notifyItemRangeInserted(0, teamsList.size());
    }

    public List<TeamModel> getTeamList(){
        return (List<TeamModel>) this.teamsList;
    }
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamlistItemViewBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.teamlist_item_view,
                        parent, false);

        binding.setCallback(teamListClickCallback);

        return new TeamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        holder.binding.setTeam(teamsList.get(position));
//        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return teamsList == null ? 0 : teamsList.size();
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {

        final TeamlistItemViewBinding binding;

        public TeamViewHolder(TeamlistItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}