package com.example.nbateamviewer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;
import com.example.nbateamviewer.networkservices.nbarepository.NBATeamRepository;

import java.util.List;

public class TeamListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<TeamModel>> teamListObservable;

    public TeamListViewModel(Application application) {
        super(application);
        teamListObservable = fetchNBATeamsList();
    }

    public MutableLiveData<List<TeamModel>> fetchNBATeamsList (){
         return NBATeamRepository.getInstance(getApplication()).getNBATeamsList();
    }
    /**
     * Expose the LiveData team details query so the UI can observe it.
     */
    public MutableLiveData<List<TeamModel>> getTeamListObservable() {
        return teamListObservable;
    }
}
