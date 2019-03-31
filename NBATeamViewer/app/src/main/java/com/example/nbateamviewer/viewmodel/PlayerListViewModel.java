package com.example.nbateamviewer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;

public class PlayerListViewModel extends AndroidViewModel {

    private TeamModel teamModel;
    public ObservableField<TeamModel> userMutableLiveData = new ObservableField<>();
    public PlayerListViewModel(Application application,TeamModel teamModel) {
        super(application);
        this.teamModel = teamModel;
        this.userMutableLiveData.set(teamModel);
    }
    /**
     * A creator is used to inject the team data into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final TeamModel teamModel;

        public Factory(@NonNull Application application, TeamModel teamModel) {
            this.application = application;
            this.teamModel = teamModel;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new PlayerListViewModel(application, teamModel);
        }
    }
}
