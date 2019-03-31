package com.example.nbateamviewer.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.PlayerslistItemViewBinding;
import com.example.nbateamviewer.networkservices.nbamodel.PlayerModel;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    List<? extends PlayerModel> playerList;
    public static final String TAG = "PlayerListAdapter";

    public PlayerListAdapter() {
    }

    public void setPlayerList(final List<? extends PlayerModel> playerList) {
        this.playerList = playerList;
        Log.d(TAG,"player list is set "+playerList.size());
        notifyItemRangeInserted(0, playerList.size());
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlayerslistItemViewBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.playerslist_item_view,
                        parent, false);

        return new PlayerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.binding.setPlayer(playerList.get(position));
//        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return playerList == null ? 0 : playerList.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {

        final PlayerslistItemViewBinding binding;

        public PlayerViewHolder(PlayerslistItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
